package com.demo.weathercheck.model.thirdparty.weatherstack

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
class WeatherstackErrorDetail {
    var info: String? = null
}