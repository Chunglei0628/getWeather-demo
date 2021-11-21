package com.demo.weathercheck.model.thirdparty.openweathermap

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
class OpenWeatherMapMain {
    var temp: BigDecimal? = null
}