package no.esa.playlistanalysis.resource.exception

import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*

@ControllerAdvice
class ExceptionHandler(@Qualifier("errorMessages") private val resourceBundle: ResourceBundle,
                       private val logger: Logger) {

    @ExceptionHandler(ApiException.UnknownErrorException::class)
    fun handle(exception: ApiException.UnknownErrorException): ResponseEntity<String> {
        logger.warn(exception.message)

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.message)
    }

    @ExceptionHandler(ApiException.BadRequestException::class)
    fun handle(exception: ApiException.BadRequestException) : ResponseEntity<String> {
        logger.warn(exception.message)

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
    }

    @ExceptionHandler(Exception::class)
    fun handle(exception: Exception): ResponseEntity<String> {
        logger.warn(exception.message)
        exception.printStackTrace()

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resourceBundle.getString("unknown"))
    }
}