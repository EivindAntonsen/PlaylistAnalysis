package no.esa.playlistanalysis.integration.spotify.model

import com.fasterxml.jackson.annotation.JsonProperty

import java.io.Serializable

class Token : Serializable {

    @JsonProperty("access_token")
    var accessToken: String? = null

    @JsonProperty("token_type")
    var tokenType: String? = null

    @JsonProperty("expires_in")
    var expiresIn = 0

    @JsonProperty("scope")
    var scope: String? = null
}