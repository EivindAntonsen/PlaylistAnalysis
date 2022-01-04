package no.esa.playlistanalysis.resource

import no.esa.playlistanalysis.annotation.Logged
import no.esa.playlistanalysis.enums.APIType
import no.esa.playlistanalysis.resource.model.GetPlaylistResponse
import no.esa.playlistanalysis.service.playlist.IPlaylistService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class PlaylistAnalysisController(private val playlistService: IPlaylistService) : PlaylistAnalysisApi {

    @Logged(APIType.EXTERNAL)
    override fun getUsersMostDanceablePlaylist(username: String): ResponseEntity<GetPlaylistResponse> {
        return playlistService.getUsersMostDanceablePlaylist(username).fold({ error ->
                               ResponseEntity
                                       .status(error.httpStatusCode)
                                       .body(GetPlaylistResponse(null, error))
                           }) { playlist ->
            ResponseEntity.ok(GetPlaylistResponse(playlist, null))
        }
    }

    override fun getUsersMostLoudPlaylist(username: String): ResponseEntity<GetPlaylistResponse> {
        TODO("Not yet implemented")
    }

    override fun getUsersMostEnergeticPlaylist(username: String): ResponseEntity<GetPlaylistResponse> {
        TODO("Not yet implemented")
    }

    override fun getUsersMostAcousticPlaylist(username: String): ResponseEntity<GetPlaylistResponse> {
        TODO("Not yet implemented")
    }

    override fun getMostInstrumentalPlaylist(username: String): ResponseEntity<GetPlaylistResponse> {
        TODO("Not yet implemented")
    }
}