package no.esa.playlistanalysis.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class SpotifySecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity.authorizeRequests { authorize ->
            authorize.anyRequest().authenticated()
        }.oauth2Login()
    }
}