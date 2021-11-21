package com.demo.weathercheck.model.thirdparty.openweathermap

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class OpenWeatherMapResponse {
    var cod: String? = null
    var message: String? = null
    var main: OpenWeatherMapMain? = null
    var wind: OpenWeatherMapWind? = null
}