package no.esa.playlistanalysis.integration.spotify.model.playlisttracks

import no.esa.playlistanalysis.integration.spotify.model.common.ExternalUrls

data class Artist(val external_urls: ExternalUrls,
                  val href: String,
                  val id: String,
                  val name: String,
                  val type: String,
                  val uri: String)