package no.esa.playlistanalysis.resource.model

import no.esa.playlistanalysis.integration.spotify.model.getplaylist.Playlist

object PlaylistMapper {
    fun toModel(playlist: Playlist): no.esa.playlistanalysis.resource.model.Playlist {
        return Playlist(playlist.collaborative,
                 playlist.description,
                 playlist.id,
                 playlist.name,
                 playlist.owner.display_name,
                 playlist.public,
                 playlist.tracks.total,
                 playlist.type,
                 playlist.uri)
    }
}