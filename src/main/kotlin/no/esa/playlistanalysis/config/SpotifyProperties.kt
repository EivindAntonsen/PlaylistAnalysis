package no.esa.playlistanalysis.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class SpotifyProperties(@Value(CLIENT_ID) val clientId: String,
                             @Value(CLIENT_SECRET) val clientSecret: String) {

    companion object {
        private const val CLIENT_ID = "\${clientId}"
        private const val CLIENT_SECRET = "\${clientSecret}"
    }
}