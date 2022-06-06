package com.erc.dominio

data class Album(
        val nome: String,
        val uriSpotify: String,
        val urlImagem: String?,
        val id: String,
        val artistas: List<Artista>,
        val dataDeLancamento: String
)
