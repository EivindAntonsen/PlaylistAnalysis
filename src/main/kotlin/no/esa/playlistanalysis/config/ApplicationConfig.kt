package no.esa.playlistanalysis.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InjectionPoint
import org.springframework.boot.SpringBootConfiguration
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Scope
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
import org.springframework.security.oauth2.core.AuthenticationMethod
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.web.client.RestTemplate
import java.util.*


@SpringBootConfiguration
@ComponentScan
class ApplicationConfig(private val spotifyProperties: SpotifyProperties) {

    @Bean
    @Scope("prototype")
    fun produceLogger(injectionPoint: InjectionPoint): Logger {
        val classOnWired = injectionPoint.member.declaringClass

        return LoggerFactory.getLogger(classOnWired)
    }

    @Bean
    fun cacheManager(): ConcurrentMapCacheManager {
        return ConcurrentMapCacheManager()
    }

    @Bean("errorMessages")
    fun resourceBundle(): ResourceBundle {
        return ResourceBundle.getBundle("messages", Locale.ENGLISH)
    }

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

    @Bean
    fun clientRegistrationRepository(): ClientRegistrationRepository {
        return InMemoryClientRegistrationRepository(this.spotifyClientRegistration())
    }

    fun spotifyClientRegistration(): ClientRegistration {
return CommonOAuth2Provider.FACEBOOK.getBuilder("spotify")
        .clientName("spotify")
        .clientId(spotifyProperties.clientId)
        .clientSecret(spotifyProperties.clientSecret)
        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
        .authorizationUri(spotifyProperties.authorizationUri)
        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
        .redirectUri(spotifyProperties.redirectUri)
        .userInfoUri(spotifyProperties.userInfoUri)
        .userInfoAuthenticationMethod(AuthenticationMethod.QUERY)
        .userNameAttributeName("display_name")
        .scope(spotifyProperties.scope)
        .tokenUri(spotifyProperties.tokenUri)
        .build()

        /*return ClientRegistration
                .withRegistrationId("spotify")
                .clientId(spotifyProperties.clientId)
                .clientSecret(spotifyProperties.clientSecret)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri(spotifyProperties.redirectUri)
                .scope("user-read-private", "user-read-email")
                .authorizationUri(spotifyProperties.authorizationUri)
                .tokenUri(spotifyProperties.tokenUri)
                .userInfoUri(spotifyProperties.userInfoUri)
                .userNameAttributeName("display_name")
                .clientName("spotify")
                .build()*/
    }
}

