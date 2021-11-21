package com.demo.weathercheck.service

import com.demo.weathercheck.model.WeatherRequest
import com.demo.weathercheck.model.WeatherResponse
import com.demo.weathercheck.service.thirdparty.AbstractThirdPartyWeatherService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WeatherService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var thirdPartyWeatherServices: List<AbstractThirdPartyWeatherService>

    fun getWeather(weatherRequest: WeatherRequest?): WeatherResponse? {
        if(weatherRequest?.city.isNullOrBlank()) {
            throw Exception("ERR_MISSING_CITY")
        }
        var result : WeatherResponse? = null
        thirdPartyWeatherServices.filter { it.isEnable() }.sortedBy { it.getSeq() }.forEach {
            if(result == null) {
                result = it.inquiryWeatherWithCache(weatherRequest?.city!!.replace(Regex("[^\\w]"), ""))
                if(result != null) {
                    weatherResponseCacheMap[weatherRequest.city!!] = result!!
                }
            }
        }
        if(result == null) {
            result = weatherResponseCacheMap[weatherRequest!!.city!!]
            if(result == null) {
                throw Exception("ERR_NO_RESULT_FOUND")
            }
        }
        return result
    }

    companion object {
        val weatherResponseCacheMap = mutableMapOf<String, WeatherResponse>()
    }
}