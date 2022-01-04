package no.esa.playlistanalysis.resource.model

data class Playlist(val collaborative: Boolean,
                    val description: String,
                    val id: String,
                    val name: String,
                    val owner: String,
                    val isPublic: Boolean,
                    val tracks: Int,
                    val type: String,
                    val uri: String)
