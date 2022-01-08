package no.esa.playlistanalysis.aspect.logged

import no.esa.playlistanalysis.annotation.Logged
import no.esa.playlistanalysis.enums.APIType
import no.esa.playlistanalysis.utils.abbreviate
import no.esa.playlistanalysis.utils.getAnnotation
import no.esa.playlistanalysis.utils.getKClass
import no.esa.playlistanalysis.utils.getLogger
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.Logger
import org.springframework.boot.logging.LogLevel
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Aspect
@Component
class LogAspect {

    companion object {
        private val DEFAULT_LOG_LEVEL = LogLevel.WARN
        private val DEFAULT_API_TYPE = APIType.INTERNAL
        private const val MAX_LENGTH_API_TYPE_EVENT_NAME = 8
    }

    @Around("@annotation(no.esa.playlistanalysis.annotation.Logged)")
    fun log(joinPoint: ProceedingJoinPoint): Any? {
        val logger = getLogger(joinPoint)
        val args = joinPoint.args.toList()
        val functionName = joinPoint.signature.name
        val kClass = getKClass(joinPoint)
        val (apiType, logLevel) = getApiTypeAndLogLevel(kClass, functionName)

        val event = LoggableEvent(kClass, functionName, args, LoggingInfo(apiType, logLevel, logger)) {
            joinPoint.proceed()
        }

        return executeAndLogEvent(event)
    }

    private fun <R> executeAndLogEvent(event: LoggableEvent<R>): R {
        val firstEventName = padEventName(event.loggingInfo.apiType.firstEventName)
        val firstEventMessage = "$firstEventName\t${event.functionName}\t${event.args.toString().abbreviate()}"

        log(firstEventMessage, event.loggingInfo.logLevel, event.loggingInfo.logger)

        val (result, duration) = executeAndMeasureTimeMillis {
            event.interceptedFunction()
        }

        val secondEventName = padEventName(event.loggingInfo.apiType.secondEventName)
        val secondEventMessage = "$secondEventName\t${result.toFormattedString()}\tin ${duration}ms."

        log(secondEventMessage, event.loggingInfo.logLevel, event.loggingInfo.logger)

        return result
    }

    fun Any?.toFormattedString(): String {
        return this?.let { result ->
            val className = getClassNameFromObject(result) ?: "Anonymous Object"

            StringBuffer().apply {
                when (result) {
                    is Collection<*> -> append("$className(${result.size})\t$result")
                    is Map<*, *> -> append("$className(${result.size})\t$result")
                    else -> append("$className\t$result")
                }
            }.toString().abbreviate()
        } ?: "null"
    }

    private fun getClassNameFromObject(any: Any?): String? = any?.let { it::class.simpleName }

    private fun <R> executeAndMeasureTimeMillis(f: () -> R): Pair<R, Long> {
        val start = System.currentTimeMillis()
        val result = f()

        return result to (System.currentTimeMillis() - start)
    }

    private fun log(message: String, logLevel: LogLevel, logger: Logger) {
        when (logLevel) {
            LogLevel.DEBUG -> logger.debug(message)
            LogLevel.INFO -> logger.info(message)
            LogLevel.ERROR -> logger.error(message)
            else -> logger.warn(message)
        }
    }

    private fun padEventName(apiEventName: String): String {
        val maxLength = APIType.values().toList().flatMap { apiType ->
            listOf(apiType.firstEventName.length, apiType.secondEventName.length)
        }.distinct().maxOrNull() ?: MAX_LENGTH_API_TYPE_EVENT_NAME

        return apiEventName.padEnd(maxLength, ' ')
    }

    private fun getApiTypeAndLogLevel(kClass: KClass<*>?, functionName: String): Pair<APIType, LogLevel> {
        return if (kClass != null) {
            val annotation = getAnnotation<Logged>(kClass, functionName)

            val apiType = annotation?.apiType ?: DEFAULT_API_TYPE
            val logLevel = annotation?.logLevel ?: DEFAULT_LOG_LEVEL

            apiType to logLevel
        } else DEFAULT_API_TYPE to DEFAULT_LOG_LEVEL
    }
}