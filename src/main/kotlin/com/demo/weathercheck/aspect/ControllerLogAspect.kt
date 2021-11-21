package com.demo.weathercheck.aspect

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.*
import javax.annotation.PostConstruct

@Aspect
@Component
class ControllerLogAspect {

    private val logger = LoggerFactory.getLogger(ControllerLogAspect::class.java)

    private val objectMapper = ObjectMapper()

    @PostConstruct
    @Throws(Exception::class)
    fun initIt() {
        val module = Hibernate5Module()
        module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION)
        objectMapper.registerModules(module)
    }

    @Before("execution(* com.demo.weathercheck.controller..*(..))")
    fun beforeLog(joinPoint: JoinPoint?) {

        try {
            val request = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes)
                    .request

            var xForwardedFor: String? = request.getHeader("X-FORWARDED-FOR")
            if (xForwardedFor == null) {
                xForwardedFor = ""
            }

            var params = ""
            try {
                val paramsMapForLogging = HashMap(request.parameterMap)
                params = objectMapper.writeValueAsString(paramsMapForLogging)

            } catch (e: Exception) {
                logger.error(e.message, e)
            }

            // String method = joinPoint.getSignature().getName();
            logger.info(
                    "[URL: {}, Method: {}]  [Host: {}, X-FORWARDED-FOR: {}]  [User: {}]  [Params: {}]",
                    request.requestURI, request.method, request.remoteHost, xForwardedFor,
                    request.remoteUser, params
            )
        } catch (e: Exception) {
            logger.error(e.message, e)
        }

    }

    @AfterReturning(pointcut = "execution(* com.demo.weathercheck.controller..*(..))", returning = "result")
    fun afterLog(joinPoint: JoinPoint?, result: Any?) {

        try {
            val request = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request

            // String method = joinPoint.getSignature().getName();

            var xForwardedFor: String? = request.getHeader("X-FORWARDED-FOR")
            if (xForwardedFor == null) {
                xForwardedFor = ""
            }

            val resultValue = ""

            logger.info(
                    "[URL: {}, Method: {}]  [Host: {}, X-FORWARDED-FOR: {}]  [User: {}]  [Return: {}]",
                    request.requestURI, request.method, request.remoteHost, xForwardedFor,
                    request.remoteUser, resultValue
            )
        } catch (e: Exception) {
            logger.error(e.message, e)
        }

    }

}
