package no.esa.playlistanalysis.resource

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import no.esa.playlistanalysis.integration.spotify.model.getplaylist.Playlist
import no.esa.playlistanalysis.resource.model.GetPlaylistResponse
import no.esa.playlistanalysis.resource.model.GetPlaylistsResponse
import no.esa.playlistanalysis.utils.ApiError
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Api(value = "Playlist Analysis", description = "Analysis of user playlists.")
@RequestMapping("/playlist-analysis", produces = ["application/json"])
interface PlaylistAnalysisApi {

    @ApiOperation(value = "Returns a users most danceable playlist", response = GetPlaylistsResponse::class)
    @ApiResponses(ApiResponse(code = 200, message = "ok", response = GetPlaylistsResponse::class),
                  ApiResponse(code = 400, message = "bad request", response = ApiError::class),
                  ApiResponse(code = 500, message = "server error", response = ApiError::class))
    @GetMapping("/danceable")
    fun getUsersMostDanceablePlaylist(username: String): ResponseEntity<GetPlaylistResponse>

    @ApiOperation(value = "Returns a users most loud playlist", response = GetPlaylistsResponse::class)
    @ApiResponses(ApiResponse(code = 200, message = "ok", response = GetPlaylistsResponse::class),
                  ApiResponse(code = 400, message = "bad request", response = ApiError::class),
                  ApiResponse(code = 500, message = "server error", response = ApiError::class))
    @GetMapping("/loud")
    fun getUsersMostLoudPlaylist(username: String): ResponseEntity<GetPlaylistResponse>

    @ApiOperation(value = "Returns a users most energetic playlist", response = GetPlaylistsResponse::class)
    @ApiResponses(ApiResponse(code = 200, message = "ok", response = GetPlaylistsResponse::class),
                  ApiResponse(code = 400, message = "bad request", response = ApiError::class),
                  ApiResponse(code = 500, message = "server error", response = ApiError::class))
    @GetMapping("/energetic")
    fun getUsersMostEnergeticPlaylist(username: String): ResponseEntity<GetPlaylistResponse>

    @ApiOperation(value = "Returns a users most acoustic playlist", response = GetPlaylistsResponse::class)
    @ApiResponses(ApiResponse(code = 200, message = "ok", response = GetPlaylistsResponse::class),
                  ApiResponse(code = 400, message = "bad request", response = ApiError::class),
                  ApiResponse(code = 500, message = "server error", response = ApiError::class))
    @GetMapping("/acoustic")
    fun getUsersMostAcousticPlaylist(username: String): ResponseEntity<GetPlaylistResponse>

    @ApiOperation(value = "Returns a users most instrumental playlist", response = GetPlaylistsResponse::class)
    @ApiResponses(ApiResponse(code = 200, message = "ok", response = GetPlaylistsResponse::class),
                  ApiResponse(code = 400, message = "bad request", response = ApiError::class),
                  ApiResponse(code = 500, message = "server error", response = ApiError::class))
    @GetMapping("/instrumental")
    fun getMostInstrumentalPlaylist(username: String): ResponseEntity<GetPlaylistResponse>

}