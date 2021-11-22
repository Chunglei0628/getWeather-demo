package com.demo.weathercheck.controller

import com.demo.weathercheck.model.WeatherRequest
import com.demo.weathercheck.model.WeatherResponse
import com.demo.weathercheck.service.WeatherService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("v1")
class WeatherController {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var weatherService: WeatherService

    @RequestMapping(value = ["/weather"], method = [RequestMethod.GET])
    @Throws(Exception::class)
    fun getWeather(weatherRequest: WeatherRequest?): WeatherResponse? {
        return weatherService.getWeather(weatherRequest)
    }

}