package com.demo.weathercheck.listener

import org.slf4j.LoggerFactory
import org.slf4j.MDC
import java.util.*
import javax.servlet.ServletRequestEvent
import javax.servlet.ServletRequestListener
import javax.servlet.annotation.WebListener

@WebListener
class RequestIdLoggingListener : ServletRequestListener {

    private val logger = LoggerFactory.getLogger(RequestIdLoggingListener::class.java)

    override fun requestInitialized(arg0: ServletRequestEvent) {
        try {
            MDC.put("RequestId", UUID.randomUUID().toString())
        } catch (e: Exception) {
            logger.error(e.message, e)
        }
    }

    override fun requestDestroyed(arg0: ServletRequestEvent) {
        try {
            MDC.clear()
        } catch (e: Exception) {
            logger.error(e.message, e)
        }
    }
}