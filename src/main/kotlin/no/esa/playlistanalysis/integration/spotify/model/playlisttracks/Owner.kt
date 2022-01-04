package no.esa.playlistanalysis.integration.spotify.model.playlisttracks

import no.esa.playlistanalysis.integration.spotify.model.common.ExternalUrls

data class Owner(val display_name: String,
                 val external_urls: ExternalUrls,
                 val href: String,
                 val id: String,
                 val type: String,
                 val uri: String)