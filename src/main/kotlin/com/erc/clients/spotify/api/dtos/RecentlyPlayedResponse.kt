package com.erc.clients.spotify.api.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class RecentlyPlayedResponse(

    @JsonProperty("items")
    val items: List<RecentlyPlayedItem>

)

data class RecentlyPlayedItem(
    @JsonProperty("track")
    val track: Track,

    @JsonProperty("played_at")
    val playedAt: String,

    @JsonProperty("context")
    val context: Context,

    @JsonProperty("timestamp")
    val timestamp: Long
)

data class Track(
    @JsonProperty("id")
    val id: String,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("artists")
    val artists: List<ArtistResponse>,

    @JsonProperty("album")
    val album: AlbumResponse,

    @JsonProperty("uri")
    val uri: String,

    @JsonProperty("external_urls")
    val externalUrls: ExternalUrls,

    @JsonProperty("duration_ms")
    val durationMs: Long,

    @JsonProperty("explicit")
    val explicit: Boolean,

    @JsonProperty("popularity")
    val popularity: Int,
)

data class Context(
    @JsonProperty("type")
    val type: String,

    @JsonProperty("href")
    val href: String,
)