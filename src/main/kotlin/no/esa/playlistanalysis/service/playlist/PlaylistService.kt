package no.esa.playlistanalysis.service.playlist

import no.esa.playlistanalysis.annotation.Logged
import no.esa.playlistanalysis.enums.APIType
import no.esa.playlistanalysis.integration.spotify.model.Playlist
import no.esa.playlistanalysis.integration.spotify.rest.ISpotifyRestInterface
import no.esa.playlistanalysis.utils.Outcome
import org.springframework.stereotype.Service

@Service
class PlaylistService(private val spotifyRestInterface: ISpotifyRestInterface) : IPlaylistService {

    @Logged(APIType.INTERNAL)
    override fun getUsersMostDanceablePlaylist(username: String): Playlist? {
        val usersPlaylists = spotifyRestInterface.getUsersPlaylists(username)

        return if (usersPlaylists is Outcome.Success) {
            usersPlaylists.value.first()
        } else null
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