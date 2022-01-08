package no.esa.playlistanalysis.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider

@Configuration
class SpotifySecurityConfig : WebSecurityConfigurerAdapter() {
    companion object {
        val PUBLIC_ANT_MATCHES = listOf("/login", "/login/*").toTypedArray()
    }

    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity.authorizeRequests()
                .antMatchers(*PUBLIC_ANT_MATCHES).permitAll().and()
                .authorizeRequests { authorize ->
                    // authorize.antMatchers("/playlist").authenticated()
                    authorize.anyRequest().authenticated()
                }.oauth2Login()
                .authorizationEndpoint().and()
                .redirectionEndpoint().baseUri("/login/oauth2/code/spotify").and()
                .tokenEndpoint().and()
                .userInfoEndpoint()
    }
}