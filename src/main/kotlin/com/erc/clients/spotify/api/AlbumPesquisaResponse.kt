package com.erc.clients.spotify.api

import com.fasterxml.jackson.annotation.JsonProperty

data class AlbumPesquisaResponse(
        @JsonProperty("albums")
        val itens: AlbumItemResponse?
)

data class AlbumItemResponse(
        @JsonProperty("items")
        val albums: List<AlbumResponse>?
)