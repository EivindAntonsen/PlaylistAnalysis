package no.esa.playlistanalysis.service.playlist

import io.vavr.control.Either
import io.vavr.control.Option
import no.esa.playlistanalysis.annotation.Logged
import no.esa.playlistanalysis.enums.APIType
import no.esa.playlistanalysis.integration.spotify.rest.ISpotifyRestInterface
import no.esa.playlistanalysis.resource.model.Playlist
import no.esa.playlistanalysis.resource.model.PlaylistMapper
import no.esa.playlistanalysis.utils.ApiError
import org.springframework.stereotype.Service

@Service
class PlaylistService(private val spotifyRestInterface: ISpotifyRestInterface) : IPlaylistService {

    @Logged(APIType.INTERNAL)
    override fun getUsersMostDanceablePlaylist(username: String): Either<ApiError, Playlist> {
        return spotifyRestInterface.getUsersPlaylists(username).map { arrayOfPlaylists ->
            PlaylistMapper.toModel(arrayOfPlaylists.playlists.random())
        }
    }

    override fun getUsersMostLoudPlaylist(username: String): Either<ApiError, List<Playlist>>  {
        TODO("Not yet implemented")
    }

    override fun getUsersMostEnergeticPlaylist(username: String): Either<ApiError, List<Playlist>>  {
        TODO("Not yet implemented")
    }

    override fun getUsersMostAcousticPlaylist(username: String): Either<ApiError, List<Playlist>>  {
        TODO("Not yet implemented")
    }

    override fun getMostInstrumentalPlaylist(username: String): Either<ApiError, List<Playlist>>  {
        TODO("Not yet implemented")
    }
}