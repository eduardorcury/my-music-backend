package com.erc.services.personal

import com.erc.clients.spotify.api.SpotifyApiClient
import com.erc.dominio.Album
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class RecentlyPlayedService(
    @Inject val spotifyClient: SpotifyApiClient
) {

    fun findRecentlyPlayedAlbums(token: String): List<Album> {
        val recentlyPlayedAlbums = spotifyClient.recentlyPlayed(token)
        return recentlyPlayedAlbums.body()
            ?.items
            ?.map { item -> item.track.album }
            ?.map { album -> album.toDomain() }
            ?: emptyList()
    }

}