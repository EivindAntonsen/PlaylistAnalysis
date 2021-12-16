package no.esa.playlistanalysis.utils

const val LOG_STRING_MAX_LENGTH = 100

fun String.abbreviate(): String {
    return if (length > LOG_STRING_MAX_LENGTH) {
        substring(0, LOG_STRING_MAX_LENGTH - 3).plus("...")
    } else this
}

