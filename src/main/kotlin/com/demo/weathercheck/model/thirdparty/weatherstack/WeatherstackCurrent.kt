package com.demo.weathercheck.model.thirdparty.weatherstack

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
class WeatherstackCurrent {
    @JsonProperty(value = "temperature")
    var temperature: BigDecimal? = null
    @JsonProperty(value = "wind_speed")
    var windSpeed: BigDecimal? = null
}