package com.erc.controllers.dtos

data class AlbumDTO(
        val nome: String,
        val uriSpotify: String,
        val urlImagem: String?,
        val id: String,
        val artistas: List<String>,
        val dataDeLancamento: String
)
