package no.esa.playlistanalysis.integration.spotify.model.playlisttracks

import no.esa.playlistanalysis.integration.spotify.model.common.ExternalUrls

data class Track(val album: Album,
                 val artists: List<Artist>,
                 val available_markets: List<String>,
                 val disc_number: Int,
                 val duration_ms: Int,
                 val episode: Boolean,
                 val explicit: Boolean,
                 val external_ids: ExternalIds,
                 val external_urls: ExternalUrls,
                 val href: String,
                 val id: String,
                 val is_local: Boolean,
                 val name: String,
                 val popularity: Int,
                 val preview_url: String,
                 val track: Boolean,
                 val track_number: Int,
                 val type: String,
                 val uri: String)