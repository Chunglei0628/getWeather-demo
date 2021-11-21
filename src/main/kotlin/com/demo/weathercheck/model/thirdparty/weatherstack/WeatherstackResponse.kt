package com.demo.weathercheck.model.thirdparty.weatherstack

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class WeatherstackResponse {
    var current: WeatherstackCurrent? = null
    var error: WeatherstackErrorDetail? = null
}