package com.erc.controllers;

import com.erc.controllers.dtos.AlbumDTO
import com.erc.controllers.dtos.SaveAlbumDTO
import com.erc.dominio.Album
import com.erc.dominio.Artista
import com.erc.dominio.SaveAlbum
import com.erc.services.personal.RecentlyPlayedService
import com.erc.services.personal.SaveAlbumService
import io.micronaut.http.HttpHeaders.AUTHORIZATION
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.Post
import jakarta.inject.Inject
import kotlinx.coroutines.runBlocking

@Controller("/albums")
class AlbumsController(
    @Inject val recentlyPlayedService: RecentlyPlayedService,
    @Inject val saveAlbumService: SaveAlbumService
) {

    @Post
    fun saveAlbum(@Body album: SaveAlbumDTO,
                  @Header(AUTHORIZATION) token: String): HttpResponse<Any> {
        saveAlbumService.saveAlbum(album.albumId, album.albumRating, token)
        return HttpResponse.ok()
    }

    @Get("/recently_played")
    fun recentlyPlayed(@Header(AUTHORIZATION) token: String) : HttpResponse<List<AlbumDTO>> {
        val recentlyPlayedAlbums = recentlyPlayedService.findRecentlyPlayedAlbums(token)
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
