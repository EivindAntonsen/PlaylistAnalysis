package no.esa.playlistanalysis.utils

sealed class Outcome<out T : Any> {
    data class Success<out T : Any>(val value: T): Outcome<T>()
    data class Error(val message: String, val cause: Throwable?) : Outcome<Nothing>()
}