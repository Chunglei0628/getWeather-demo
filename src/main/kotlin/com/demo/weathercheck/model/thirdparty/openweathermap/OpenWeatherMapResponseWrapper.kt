package com.demo.weathercheck.model.thirdparty.openweathermap

import com.demo.weathercheck.model.WeatherResponse
import org.springframework.stereotype.Component

@Component
class OpenWeatherMapResponseWrapper {

    fun convert(openWeatherMapResponse: OpenWeatherMapResponse?): WeatherResponse {
        return WeatherResponse().apply {
            this.temperatureDegrees = openWeatherMapResponse?.main?.temp
            this.windSpeed = openWeatherMapResponse?.wind?.speed
        }
    }
}