package com.demo.weathercheck

import com.demo.weathercheck.config.CoreConfiguration
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.ResourceHttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.ArrayList

@SpringBootApplication
@EnableCaching
@EnableScheduling
@Import(CoreConfiguration::class)
class WeatherCheckApplication

fun main(args: Array<String>) {
	runApplication<WeatherCheckApplication>(*args)
}
