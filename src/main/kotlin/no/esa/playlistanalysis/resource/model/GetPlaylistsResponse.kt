package no.esa.playlistanalysis.resource.model

import no.esa.playlistanalysis.utils.ApiError

class GetPlaylistsResponse(val playlists: List<Playlist>, val error: ApiError? = null)