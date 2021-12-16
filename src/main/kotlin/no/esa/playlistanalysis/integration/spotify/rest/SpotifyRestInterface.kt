package no.esa.playlistanalysis.integration.spotify.rest

import no.esa.playlistanalysis.annotation.Logged
import no.esa.playlistanalysis.config.SpotifyProperties
import no.esa.playlistanalysis.enums.APIType
import no.esa.playlistanalysis.integration.spotify.model.ArrayOfPlaylist
import no.esa.playlistanalysis.integration.spotify.model.AudioFeatures
import no.esa.playlistanalysis.utils.Outcome
import org.slf4j.Logger
import org.springframework.http.MediaType
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import org.springframework.web.util.UriComponentsBuilder

@Component
class SpotifyRestInterface(private val logger: Logger,
                           private val spotifyProperties: SpotifyProperties,
                           private val clientRegistrationRepository: InMemoryClientRegistrationRepository,
                           private val authorizedClientService: OAuth2AuthorizedClientService) : ISpotifyRestInterface {

    companion object {

        private const val BASE_URL = "https://api.spotify.com/v1/"
        private const val ACCESS_TOKEN_URL = "https://accounts.spotify.com/api/token"
        private val USER_PLAYLIST_PATH = { userName: String -> "users/$userName/playlists" }
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

    private fun buildUri(url: String, parameters: Map<String, String>): String {
        return UriComponentsBuilder.fromHttpUrl(url).apply {
            parameters.forEach { (key, value) -> queryParam(key, value) }
        }.build().toUriString()
    }

    @Logged(APIType.EXTERNAL)
    private inline fun <reified T : Any> getForEntity(uri: String): Outcome<T> {
        return try {
            val response = restTemplate().getForEntity<T>(uri)

            if (response.statusCode.is2xxSuccessful && response.body != null) {
                Outcome.Success(response.body!!)
            } else Outcome.Error(response.statusCode.reasonPhrase, null)
        } catch (exception: Exception) {
            val message = exception.message

            logger.error(message)

            Outcome.Error(message ?: "Failed to get entity at uri: $uri", exception)
        }
    }

    override fun getUsersPlaylists(username: String): Outcome<ArrayOfPlaylist> {
        val uri = buildUri(BASE_URL + USER_PLAYLIST_PATH(username), emptyMap())

        return getForEntity(uri)
    }

    override fun getTracksAudioFeatures(songId: String): Outcome<AudioFeatures> {
        val uri = buildUri(BASE_URL + AUDIO_FEATURES_PATH(songId), emptyMap())

        return getForEntity(uri)
    }
}