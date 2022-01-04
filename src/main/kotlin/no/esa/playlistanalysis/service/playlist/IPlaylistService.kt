package no.esa.playlistanalysis.service.playlist

import io.vavr.control.Either
import no.esa.playlistanalysis.integration.spotify.model.getplaylist.Playlist
import no.esa.playlistanalysis.utils.ApiError

interface IPlaylistService {

    fun getUsersMostDanceablePlaylist(username: String): Either<ApiError, no.esa.playlistanalysis.resource.model.Playlist>
    fun getUsersMostLoudPlaylist(username: String): Either<ApiError, List<no.esa.playlistanalysis.resource.model.Playlist>>
    fun getUsersMostEnergeticPlaylist(username: String): Either<ApiError, List<no.esa.playlistanalysis.resource.model.Playlist>>
    fun getUsersMostAcousticPlaylist(username: String): Either<ApiError, List<no.esa.playlistanalysis.resource.model.Playlist>>
    fun getMostInstrumentalPlaylist(username: String): Either<ApiError, List<no.esa.playlistanalysis.resource.model.Playlist>>

}