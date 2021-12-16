package no.esa.playlistanalysis.integration.spotify.rest

import no.esa.playlistanalysis.integration.spotify.model.AudioFeatures
import no.esa.playlistanalysis.integration.spotify.model.ArrayOfPlaylist
import no.esa.playlistanalysis.integration.spotify.model.Playlist
import no.esa.playlistanalysis.utils.Outcome

interface ISpotifyRestInterface {

    fun getUsersPlaylists(username: String): Outcome<ArrayOfPlaylist>

    fun getTracksAudioFeatures(songId: String): Outcome<AudioFeatures>
}