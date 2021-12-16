package no.esa.playlistanalysis.service.playlist

import no.esa.playlistanalysis.integration.spotify.model.Playlist

interface IPlaylistService {

    fun getUsersMostDanceablePlaylist(username: String): Playlist?
    fun getUsersMostLoudPlaylist(username: String): Playlist
    fun getUsersMostEnergeticPlaylist(username: String): Playlist
    fun getUsersMostAcousticPlaylist(username: String): Playlist
    fun getMostInstrumentalPlaylist(username: String): Playlist

}