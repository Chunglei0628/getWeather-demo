package com.demo.weathercheck.service.thirdparty

import com.demo.weathercheck.model.WeatherResponse
import com.demo.weathercheck.model.thirdparty.weatherstack.WeatherResponseWrapper
import com.demo.weathercheck.model.thirdparty.openweathermap.OpenWeatherMapResponse
import com.demo.weathercheck.model.thirdparty.openweathermap.OpenWeatherMapResponseWrapper
import com.demo.weathercheck.properties.ApplicationProperties
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class OpenWeatherMapService : AbstractThirdPartyWeatherService() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var openWeatherMapResponseWrapper: OpenWeatherMapResponseWrapper

    @Autowired
    private lateinit var applicationProperties: ApplicationProperties

    override fun getSeq(): Int {
        return applicationProperties.thirdPartyProperties.openWeatherMap?.sequence!!
    }

    override fun isEnable(): Boolean {
        var result = false
        if(applicationProperties.thirdPartyProperties.openWeatherMap?.enable == "Y") {
            result = true
        }
        return result
    }

    @Cacheable("OpenWeatherMapService_inquiryWeather")
    override fun inquiryWeatherWithCache(city: String): WeatherResponse? {
        var result: WeatherResponse? = null
        try {
            var restTemplate = RestTemplate()
            val units = unitMap[applicationProperties.thirdPartyProperties.temperatureUnit] ?: throw Exception("ERR_INVALID_TEMPERATURE_UNIT")
            var response = restTemplate.getForEntity("http://api.openweathermap.org/data/2.5/weather?q=$city&appid=${applicationProperties.thirdPartyProperties.openWeatherMap?.apiKey}${
                if(!units.isBlank()) {
                    "&units=$units"
                }
                else {
                    ""
                }
            }", OpenWeatherMapResponse::class.java)
            if(response.body.cod != "200") {
                throw Exception(response.body.message)
            }
            result = openWeatherMapResponseWrapper.convert(response.body)
        } catch(e: Exception) {
            logger.error(e.message)
        }
        return result
    }

    @Scheduled(cron = "\${app.thirdPartyProperties.cacheClearSchedule}")
    @CacheEvict(value= ["OpenWeatherMapService_inquiryWeather"], allEntries=true)
    override fun clearInquiryWeatherWithCache() {
    }

    companion object {
        val unitMap = mapOf("celsius" to "metric","kelvin" to "", "fahrenheit" to "imperial")
    }

}