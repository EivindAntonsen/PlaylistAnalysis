package no.esa.playlistanalysis.enums


/**
 * Used for prefix in log messages to indicate the origin and destination of a logged event.
 *
 * @property INTERNAL is for api calls that happened within this module.
 * @property EXTERNAL is for api calls that either originate from or have an outbound destination.
 */
enum class APIType(val firstEventName: String, val secondEventName: String) {
    INTERNAL("Call", "Result"),
    EXTERNAL("Request", "Response")
}