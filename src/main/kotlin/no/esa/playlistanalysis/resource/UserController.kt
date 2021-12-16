package no.esa.playlistanalysis.resource

import no.esa.playlistanalysis.annotation.Logged
import no.esa.playlistanalysis.enums.APIType
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @Logged(APIType.EXTERNAL)
    @RequestMapping("/user", produces = ["application/json"])
    fun user(@AuthenticationPrincipal
             principal: OAuth2User,
             @RequestHeader requestHeader: RequestHeader): Map<String, Any?> {
        println(requestHeader)
        return mapOf("name" to principal.getAttribute<Any>("display_name"))
    }

    @Logged(APIType.EXTERNAL)
    @RequestMapping("/login/oauth2/code/")
    fun login(@RequestParam code: String) {
        println("This is the code: $code")
    }

    @Logged(APIType.EXTERNAL)
    @RequestMapping("/login/oauth2/code/")
    fun login(@RequestParam error: String,
              @RequestParam state: String) {
        println("This is the error: $error")
    }
}