package com.demo.weathercheck.properties

import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
@ConfigurationProperties("app")
class ApplicationProperties {

    private val logger = LoggerFactory.getLogger(javaClass)

    var thirdPartyProperties : ThirdPartyProperties = ThirdPartyProperties()
    @PostConstruct
    fun postConstruct() {
    }
}