package no.esa.playlistanalysis.integration.spotify.model.playlisttracks

import no.esa.playlistanalysis.integration.spotify.model.common.ExternalUrls
import no.esa.playlistanalysis.integration.spotify.model.common.Image

data class Album(val album_type: String,
                 val artists: List<Artist>,
                 val available_markets: List<String>,
                 val external_urls: ExternalUrls,
                 val href: String,
                 val id: String,
                 val images: List<Image>,
                 val name: String,
                 val release_date: String,
                 val release_date_precision: String,
                 val total_tracks: Int,
                 val type: String,
                 val uri: String)