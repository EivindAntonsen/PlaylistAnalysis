package no.esa.playlistanalysis.aspect.logged

import no.esa.playlistanalysis.enums.APIType
import org.slf4j.Logger
import org.springframework.boot.logging.LogLevel

/**
 * Contains data about how the logging should be done.
 *
 * @param apiType   tells us if the event was internal or external.
 * @param logLevel  is the severity of the event.
 * @param logger    is an instance of a logger appropriate for the event.
 */
data class LoggingInfo(val apiType: APIType,
                       val logLevel: LogLevel,
                       val logger: Logger)