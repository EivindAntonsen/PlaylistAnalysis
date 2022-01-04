package no.esa.playlistanalysis.integration.spotify.model.playlisttracks

data class Tracks(val href: String,
                  val items: List<Item>,
                  val limit: Int,
                  val next: Any,
                  val offset: Int,
                  val previous: Any,
                  val total: Int)