package no.esa.playlistanalysis.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class SpotifyProperties(@Value(CLIENT_ID) val clientId: String,
                             @Value(CLIENT_SECRET) val clientSecret: String,
                             @Value(REDIRECT_URI) val redirectUri: String,
                             @Value(AUTHORIZATION_URI) val authorizationUri: String,
                             @Value(TOKEN_URI) val tokenUri: String,
                             @Value(USER_INFO_URI) val userInfoUri: String) {

    companion object {

        private const val CLIENT_ID = "\${clientId}"
        private const val CLIENT_SECRET = "\${clientSecret}"
        private const val REDIRECT_URI = "\${redirectUri}"
        private const val AUTHORIZATION_URI = "\${authorizationUri}"
        private const val TOKEN_URI = "\${tokenUri}"
        private const val USER_INFO_URI = "\${userInfoUri}"
    }
}