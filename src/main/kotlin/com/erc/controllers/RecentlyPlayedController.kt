package com.erc.controllers;

import com.erc.controllers.dtos.AlbumDTO
import com.erc.dominio.Album
import com.erc.dominio.Artista
import com.erc.services.personal.RecentlyPlayedService
import io.micronaut.http.HttpHeaders.AUTHORIZATION
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import jakarta.inject.Inject

@Controller
class RecentlyPlayedController(
    @Inject val service: RecentlyPlayedService
) {

    @Get("/recently_played")
    fun recentlyPlayed(@Header(AUTHORIZATION) token: String) : HttpResponse<List<AlbumDTO>> {
        val recentlyPlayedAlbums = service.findRecentlyPlayedAlbums(token)
        return HttpResponse.ok(recentlyPlayedAlbums.map { item: Album -> item.toDTO() }.distinct())
    }

    private fun Album.toDTO() : AlbumDTO {
        return AlbumDTO(
            nome = nome,
            uriSpotify = uriSpotify,
            urlImagem = urlImagem,
            id = id,
            artistas = artistas.map { artista: Artista -> artista.nome }.toList(),
            dataDeLancamento = dataDeLancamento
        )
    }

}
