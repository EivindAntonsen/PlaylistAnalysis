package no.esa.playlistanalysis.resource.model

import no.esa.playlistanalysis.utils.ApiError

data class GetPlaylistResponse(val playlist: Playlist?, val error: ApiError? = null)