package no.esa.playlistanalysis.annotation

import no.esa.playlistanalysis.enums.APIType
import org.springframework.boot.logging.LogLevel

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Logged(val apiType: APIType = APIType.INTERNAL,
                        val logLevel: LogLevel = LogLevel.DEBUG)
