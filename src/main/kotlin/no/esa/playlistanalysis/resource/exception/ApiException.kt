package no.esa.playlistanalysis.resource.exception

sealed class ApiException : RuntimeException() {

    abstract val httpStatusCode: Int

    data class UnknownErrorException(override val message: String?,
                                     override val cause: Throwable?) : ApiException() {

        override val httpStatusCode: Int = 500
    }

    data class BadRequestException(override val message: String?,
                                     override val cause: Throwable?) : ApiException() {

        override val httpStatusCode: Int = 400
    }
}