package com.demo.weathercheck.config

import org.slf4j.LoggerFactory
import org.springframework.boot.web.servlet.ServletComponentScan


@ServletComponentScan("com.demo.weathercheck")
class CoreConfiguration {
    private val logger = LoggerFactory.getLogger(javaClass)

}