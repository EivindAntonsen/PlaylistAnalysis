package no.esa.playlistanalysis.integration.spotify.model

data class ArrayOfPlaylist(val href: String,
                           val playlists: List<Playlist>,
                           val limit: Int,
                           val next: String,
                           val offset: Int,
                           val previous: String,
                           val total: Int): Iterable<Playlist> {

    override fun iterator(): Iterator<Playlist> = playlists.iterator()
}