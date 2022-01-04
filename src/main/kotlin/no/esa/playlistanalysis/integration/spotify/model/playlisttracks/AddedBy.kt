package no.esa.playlistanalysis.integration.spotify.model.playlisttracks

import no.esa.playlistanalysis.integration.spotify.model.common.ExternalUrls

data class AddedBy(val external_urls: ExternalUrls,
                   val href: String,
                   val id: String,
                   val type: String,
                   val uri: String)