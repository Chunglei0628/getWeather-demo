package com.demo.weathercheck.advice

import com.demo.weathercheck.model.error.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class RestControllerAdvice {

    private val logger = LoggerFactory.getLogger(RestControllerAdvice::class.java)

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun exception(e: Exception): ErrorResponse {
        logger.error(e.message, e)

        return ErrorResponse().apply {
            this.errorCode = "500"
            this.errorMsg = e.message
        }
    }

}
