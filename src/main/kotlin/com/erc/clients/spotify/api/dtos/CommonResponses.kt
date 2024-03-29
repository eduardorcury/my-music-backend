package com.erc.clients.spotify.api.dtos

import com.erc.dominio.Album
import com.erc.dominio.Artista
import com.fasterxml.jackson.annotation.JsonProperty

data class AlbumResponse(
    @JsonProperty("artists")
    val artistas: List<ArtistResponse>,

    @JsonProperty("id")
    val id: String,

    @JsonProperty("images")
    val imagens: List<ImageResponse>?,

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
            urlImagem = this.imagens?.firstOrNull { imagem -> imagem.comprimento == "300" }?.uri,
            id = this.id,
            artistas = this.artistas.map { artista -> Artista(nome = artista.nome) }.toList(),
            dataDeLancamento = this.dataDeLancamento
        )
    }
}

data class ArtistResponse(
    @JsonProperty("name")
    val nome: String
)

data class ImageResponse(
    @JsonProperty("height")
    val altura: String,

    @JsonProperty("width")
    val comprimento: String,

    @JsonProperty("url")
    val uri: String
)

data class ExternalUrls(
    @JsonProperty("spotify")
    val spotify: String
)
