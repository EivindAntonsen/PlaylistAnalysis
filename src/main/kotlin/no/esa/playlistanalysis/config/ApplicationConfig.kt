package no.esa.playlistanalysis.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InjectionPoint
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.system.SystemProperties
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.Scope
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import java.util.*

@SpringBootConfiguration
@ComponentScan
@PropertySource("file:src/main/resources/properties/application.properties")
class ApplicationConfig {

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
    fun clientRegistrationRepository(): ClientRegistrationRepository {
        return InMemoryClientRegistrationRepository(this.spotifyClientRegistration())
    }

    private fun spotifyClientRegistration(): ClientRegistration {
        return ClientRegistration
                .withRegistrationId("spotify")
                .clientId(SystemProperties.get("clientId"))
                .clientSecret(SystemProperties.get("clientSecret"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://localhost:8098/login/oauth2/code/")
                .scope("user-read-private", "user-read-email")
                .authorizationUri("https://accounts.spotify.com/authorize")
                .tokenUri("https://accounts.spotify.com/api/token")
                .userInfoUri("https://api.spotify.com/v1/me")
                .userNameAttributeName("display_name")
                .clientName("spotify")
                .build()
    }
}

