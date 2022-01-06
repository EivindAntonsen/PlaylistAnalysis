package no.esa.playlistanalysis.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class SpotifySecurityConfig : WebSecurityConfigurerAdapter() {
    companion object {
        val PUBLIC_ANT_MATCHES = listOf("/*", "/register", "/login", "/home", "/session").toTypedArray()
    }

    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity.authorizeRequests()
                .antMatchers(*PUBLIC_ANT_MATCHES).permitAll()
                .antMatchers("/admin").hasAuthority("ADMIN")
                .and().authorizeRequests { authorize ->
                    authorize.anyRequest().authenticated()
                }.oauth2Login()
    }
}