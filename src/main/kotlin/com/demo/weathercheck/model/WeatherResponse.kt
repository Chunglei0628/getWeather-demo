package com.demo.weathercheck.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

class WeatherResponse {
    @JsonProperty(value = "wind_speed")
    var windSpeed: BigDecimal? = null
    @JsonProperty(value = "temperature_degrees")
    var temperatureDegrees: BigDecimal? = null
}