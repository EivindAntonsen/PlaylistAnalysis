package no.esa.playlistanalysis.resource

import no.esa.playlistanalysis.annotation.Logged
import no.esa.playlistanalysis.enums.APIType
import no.esa.playlistanalysis.integration.spotify.model.Playlist
import no.esa.playlistanalysis.service.playlist.IPlaylistService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class PlaylistAnalysisController(private val playlistService: IPlaylistService) : PlaylistAnalysisApi {

    @Logged(APIType.EXTERNAL)
    override fun getUsersMostDanceablePlaylist(username: String): ResponseEntity<Playlist> {
        val result = playlistService.getUsersMostDanceablePlaylist(username)

        return ResponseEntity.ok(result)
    }

    override fun getUsersMostLoudPlaylist(username: String): Playlist {
        TODO("Not yet implemented")
    }

    override fun getUsersMostEnergeticPlaylist(username: String): Playlist {
        TODO("Not yet implemented")
    }

    override fun getUsersMostAcousticPlaylist(username: String): Playlist {
        TODO("Not yet implemented")
    }

    override fun getMostInstrumentalPlaylist(username: String): Playlist {
        TODO("Not yet implemented")
    }
}