package com.demo.weathercheck.model.thirdparty.weatherstack

import com.demo.weathercheck.model.WeatherResponse
import org.springframework.stereotype.Component

@Component
class WeatherResponseWrapper {

    fun convert(weatherstackResponse: WeatherstackResponse?): WeatherResponse {
        return WeatherResponse().apply {
            this.temperatureDegrees = weatherstackResponse?.current?.temperature
            this.windSpeed = weatherstackResponse?.current?.windSpeed
        }
    }
}