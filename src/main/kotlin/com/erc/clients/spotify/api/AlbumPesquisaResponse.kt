package com.erc.clients.spotify.api

import com.erc.dominio.Album
import com.erc.dominio.Artista
import com.fasterxml.jackson.annotation.JsonProperty

data class AlbumPesquisaResponse(
        @JsonProperty("albums")
        val itens: AlbumItemResponse?
)

data class AlbumItemResponse(
        @JsonProperty("items")
        val albums: List<AlbumResponse>?
)

data class AlbumResponse(
        @JsonProperty("artists")
        val artistas: List<ArtistaResponse>,

        @JsonProperty("id")
        val id: String,

        @JsonProperty("images")
        val imagens: List<ImagemResponse>?,

        @JsonProperty("name")
        val nome: String,

        @JsonProperty("release_date")
        val dataDeLancamento: String,

        @JsonProperty("uri")
        val uriSpotify: String
) {
        fun toDomain(): Album {
                return Album(
                        nome = this.nome,
                        uriSpotify = this.uriSpotify,
                        urlImagem = this.imagens?.firstOrNull()?.uri,
                        id = this.id,
                        artistas = this.artistas.map { artista -> Artista(nome = artista.nome) }.toList(),
                        dataDeLancamento = this.dataDeLancamento
                )
        }
}

data class ArtistaResponse(
        @JsonProperty("name")
        val nome: String
)

data class ImagemResponse(
        @JsonProperty("height")
        val altura: String,

        @JsonProperty("width")
        val comprimento: String,

        @JsonProperty("url")
        val uri: String
)
