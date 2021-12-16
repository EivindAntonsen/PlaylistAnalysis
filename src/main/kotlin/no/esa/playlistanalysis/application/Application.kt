package no.esa.playlistanalysis.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication(scanBasePackages = ["no.esa.playlistanalysis"])
@EnableCaching
@EnableWebMvc
@ComponentScan(basePackages = ["no.esa.playlistanalysis"])
class PlaylistAnalysisApplication

fun main(args: Array<String>) {
    runApplication<PlaylistAnalysisApplication>(*args)
}
