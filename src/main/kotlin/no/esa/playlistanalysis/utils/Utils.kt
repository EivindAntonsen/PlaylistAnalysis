package no.esa.playlistanalysis.utils

import io.vavr.control.Either

fun <R> tryEither(f: () -> R): Either<Exception, R> {
    return try {
        Either.right(f())
    } catch (exception: Exception) {
        Either.left(exception)
    }
}