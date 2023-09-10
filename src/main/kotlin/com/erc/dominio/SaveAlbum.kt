package com.erc.dominio

data class SaveAlbum(
    val id: String,
    val rating: String,
    var userId: String,
    var nome: String,
    var uriSpotify: String,
    var urlImagem: String?,
    var artistas: List<Artista>,
    var dataDeLancamento: String
) {
    constructor(id: String, rating: String, userId: String, album: Album) : this(
        id, rating, userId, album.nome, album.uriSpotify, album.urlImagem, album.artistas, album.dataDeLancamento
    )
}