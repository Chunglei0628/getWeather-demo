package com.demo.weathercheck.service.thirdparty

import com.demo.weathercheck.model.WeatherResponse
import com.demo.weathercheck.model.thirdparty.weatherstack.WeatherResponseWrapper
import com.demo.weathercheck.model.thirdparty.weatherstack.WeatherstackResponse
import com.demo.weathercheck.properties.ApplicationProperties
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class WeatherstackService : AbstractThirdPartyWeatherService() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var weatherResponseWrapper: WeatherResponseWrapper

    @Autowired
    private lateinit var applicationProperties: ApplicationProperties

    override fun getSeq(): Int {
        return applicationProperties.thirdPartyProperties.weatherstack?.sequence!!
    }

    override fun isEnable(): Boolean {
        var result = false
        if(applicationProperties.thirdPartyProperties.weatherstack?.enable == "Y") {
            result = true
        }
        return result
    }

    @Cacheable("WeatherstackService_inquiryWeather")
    override fun inquiryWeatherWithCache(city: String): WeatherResponse? {
        var result: WeatherResponse? = null
        try {
            var restTemplate = RestTemplate()
            val units = unitMap[applicationProperties.thirdPartyProperties.temperatureUnit] ?: throw Exception("ERR_INVALID_TEMPERATURE_UNIT")
            var response = restTemplate.getForEntity("http://api.weatherstack.com/current?access_key=${applicationProperties.thirdPartyProperties.weatherstack?.apiKey}&query=$city&units=$units", WeatherstackResponse::class.java)
            if(response.body.error?.info != null) {
                throw Exception(response.body.error?.info)
            }
            result = weatherResponseWrapper.convert(response.body)
        } catch(e: Exception) {
            logger.error(e.message)
        }
        return result
    }

    @Scheduled(cron = "\${app.thirdPartyProperties.cacheClearSchedule}")
    @CacheEvict(value= ["WeatherstackService_inquiryWeather"], allEntries=true)
    override fun clearInquiryWeatherWithCache() {
    }

    companion object {
        val unitMap = mapOf("celsius" to "m","kelvin" to "s", "fahrenheit" to "f")
    }

}