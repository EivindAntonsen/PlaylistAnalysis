package no.esa.playlistanalysis.integration.spotify.rest

import io.vavr.control.Either
import no.esa.playlistanalysis.integration.spotify.model.getplaylist.ArrayOfPlaylist
import no.esa.playlistanalysis.integration.spotify.model.getplaylist.AudioFeatures
import no.esa.playlistanalysis.utils.ApiError

interface ISpotifyRestInterface {

    fun getUsersPlaylists(username: String): Either<ApiError, ArrayOfPlaylist>

    fun getTracksAudioFeatures(songId: String): Either<ApiError, AudioFeatures>
}