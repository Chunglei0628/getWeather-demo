package com.demo.weathercheck.service.thirdparty

import com.demo.weathercheck.model.WeatherResponse

interface ThirdPartyWeatherService {
    fun getSeq(): Int
    fun isEnable(): Boolean
    fun inquiryWeatherWithCache(city: String): WeatherResponse?
    fun clearInquiryWeatherWithCache()

}