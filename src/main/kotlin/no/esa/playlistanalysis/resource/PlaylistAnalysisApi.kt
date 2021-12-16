package no.esa.playlistanalysis.resource

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import no.esa.playlistanalysis.integration.spotify.model.ArrayOfPlaylist
import no.esa.playlistanalysis.integration.spotify.model.Playlist
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Api(value = "Playlist Analysis", description = "Analysis of user playlists.")
@RequestMapping("/playlist-analysis", produces = ["application/json"])
interface PlaylistAnalysisApi {

    @ApiOperation(value = "Returns a users most danceable playlist", response = Playlist::class)
    @ApiResponses(ApiResponse(code = 200, message = "ok", response = Playlist::class),
                  ApiResponse(code = 400, message = "bad request", response = Exception::class),
                  ApiResponse(code = 500, message = "server error", response = Exception::class))
    @GetMapping("/danceable")
    fun getUsersMostDanceablePlaylist(username: String): ResponseEntity<Playlist>

    @ApiOperation(value = "Returns a users most loud playlist", response = Playlist::class)
    @ApiResponses(ApiResponse(code = 200, message = "ok", response = Playlist::class),
                  ApiResponse(code = 400, message = "bad request", response = Exception::class),
                  ApiResponse(code = 500, message = "server error", response = Exception::class))
    @GetMapping("/loud")
    fun getUsersMostLoudPlaylist(username: String): Playlist

    @ApiOperation(value = "Returns a users most energetic playlist", response = Playlist::class)
    @ApiResponses(ApiResponse(code = 200, message = "ok", response = Playlist::class),
                  ApiResponse(code = 400, message = "bad request", response = Exception::class),
                  ApiResponse(code = 500, message = "server error", response = Exception::class))
    @GetMapping("/energetic")
    fun getUsersMostEnergeticPlaylist(username: String): Playlist

    @ApiOperation(value = "Returns a users most acoustic playlist", response = Playlist::class)
    @ApiResponses(ApiResponse(code = 200, message = "ok", response = Playlist::class),
                  ApiResponse(code = 400, message = "bad request", response = Exception::class),
                  ApiResponse(code = 500, message = "server error", response = Exception::class))
    @GetMapping("/acoustic")
    fun getUsersMostAcousticPlaylist(username: String): Playlist

    @ApiOperation(value = "Returns a users most instrumental playlist", response = Playlist::class)
    @ApiResponses(ApiResponse(code = 200, message = "ok", response = Playlist::class),
                  ApiResponse(code = 400, message = "bad request", response = Exception::class),
                  ApiResponse(code = 500, message = "server error", response = Exception::class))
    @GetMapping("/instrumental")
    fun getMostInstrumentalPlaylist(username: String): Playlist

}