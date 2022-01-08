package no.esa.playlistanalysis.integration.spotify.rest

import io.vavr.control.Either
import no.esa.playlistanalysis.annotation.Logged
import no.esa.playlistanalysis.enums.APIType
import no.esa.playlistanalysis.integration.spotify.model.getplaylist.ArrayOfPlaylist
import no.esa.playlistanalysis.integration.spotify.model.getplaylist.AudioFeatures
import no.esa.playlistanalysis.utils.ApiError
import no.esa.playlistanalysis.utils.tryEither
import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.security.oauth2.client.authentication.OAuth2AuthorizationCodeReactiveAuthenticationManager
import org.springframework.security.oauth2.client.authentication.OAuth2LoginReactiveAuthenticationManager
import org.springframework.security.oauth2.core.OAuth2Token
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import org.springframework.web.util.UriComponentsBuilder

@Component
class SpotifyRestInterface(private val logger: Logger) : ISpotifyRestInterface {

    companion object {
        private const val BASE_URL = "https://api.spotify.com/v1/"
        private val USER_PLAYLIST_PATH = { userName: String -> "users/$userName/playlists" }
        private val PLAYLIST_TRACKS_PATH = { playlistId: String -> "playlists/$playlistId/tracks" }
        private val AUDIO_FEATURES_PATH = { songId: String -> "audio-features/${songId}" }
    }

    private fun restTemplate(): RestTemplate {
        return RestTemplate().apply {
            ClientHttpRequestInterceptor { request, body, execution ->
                request.headers.contentType = MediaType.APPLICATION_JSON
                execution.execute(request, body)
            }
        }
    }

    @Logged(APIType.EXTERNAL)
    private inline fun <reified T : Any> getForEntity(uri: String): Either<Exception, T> {
        return tryEither {
            restTemplate().getForEntity<T>(uri).body!!
        }
    }

    private fun buildUri(url: String, parameters: Map<String, String>): String {
        return UriComponentsBuilder.fromHttpUrl(url).apply {
            parameters.forEach { (key, value) -> queryParam(key, value) }
        }.build().toUriString()
    }

    override fun getUsersPlaylists(username: String): Either<ApiError, ArrayOfPlaylist> {
        val uri = buildUri(BASE_URL + USER_PLAYLIST_PATH(username), emptyMap())

        val token: OAuth2AuthenticationToken = SecurityContextHolder.getContext().authentication as OAuth2AuthenticationToken

        println(SecurityContextHolder.getContext().authentication::class.qualifiedName)



        return getForEntity<ArrayOfPlaylist>(uri).mapLeft { exception ->
            ApiError("Failed to get playlists: ${exception.message}", HttpStatus.INTERNAL_SERVER_ERROR.value())
        }
    }

    override fun getTracksAudioFeatures(songId: String): Either<ApiError, AudioFeatures> {
        val uri = buildUri(BASE_URL + AUDIO_FEATURES_PATH(songId), emptyMap())

        return getForEntity<AudioFeatures>(uri).mapLeft { exception ->
            ApiError("Failed to get audio features: ${exception.message}", HttpStatus.INTERNAL_SERVER_ERROR.value())
        }
    }

    fun getPlaylistsTracks(playlistId: String) {
        val uri = buildUri(BASE_URL + PLAYLIST_TRACKS_PATH(playlistId), emptyMap())

    }
}