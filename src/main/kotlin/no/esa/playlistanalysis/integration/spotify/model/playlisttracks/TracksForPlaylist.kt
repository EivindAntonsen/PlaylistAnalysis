package no.esa.playlistanalysis.integration.spotify.model.playlisttracks

import no.esa.playlistanalysis.integration.spotify.model.common.ExternalUrls
import no.esa.playlistanalysis.integration.spotify.model.common.Image

data class TracksForPlaylist(val collaborative: Boolean,
                             val description: String,
                             val external_urls: ExternalUrls,
                             val followers: Followers,
                             val href: String,
                             val id: String,
                             val images: List<Image>,
                             val name: String,
                             val owner: Owner,
                             val primary_color: Any,
                             val `public`: Boolean,
                             val snapshot_id: String,
                             val tracks: Tracks,
                             val type: String,
                             val uri: String)