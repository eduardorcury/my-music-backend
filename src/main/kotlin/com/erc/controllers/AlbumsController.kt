package com.erc.controllers;

import com.erc.controllers.dtos.AlbumDTO
import com.erc.controllers.dtos.SaveAlbumDTO
import com.erc.dominio.Album
import com.erc.dominio.Artista
import com.erc.services.personal.GetAlbumService
import com.erc.services.personal.RecentlyPlayedService
import com.erc.services.personal.SaveAlbumService
import io.micronaut.http.HttpHeaders.AUTHORIZATION
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import jakarta.inject.Inject

@Controller("/albums")
class AlbumsController(
    @Inject val recentlyPlayedService: RecentlyPlayedService,
    @Inject val saveAlbumService: SaveAlbumService,
    @Inject val getAlbumService: GetAlbumService
) {
    @Get
    fun getAlbums(@Header(AUTHORIZATION) token: String) : HttpResponse<List<AlbumDTO>> {
        val albums = getAlbumService.getAlbums(token)
        return HttpResponse.ok(albums.map { item -> item.toDTO() })
    }

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
            dataDeLancamento = dataDeLancamento,
            nota = nota
        )
    }

}
