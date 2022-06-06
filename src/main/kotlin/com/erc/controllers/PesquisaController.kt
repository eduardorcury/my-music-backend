package com.erc.controllers

import com.erc.dominio.Album
import com.erc.dominio.Artista
import com.erc.controllers.dtos.AlbumDTO
import com.erc.services.pesquisa.PesquisaService
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.http.annotation.QueryValue
import jakarta.inject.Inject

@Controller
class PesquisaController(
        @Inject val pesquisaService: PesquisaService
) {

    @Get("/pesquisa")
    @Produces(MediaType.APPLICATION_JSON)
    fun pesquisaPorAlbum(@QueryValue("album") album: String) : HttpResponse<List<AlbumDTO>> {
        val albums = pesquisaService.pesquisaPorAlbum(album) ?: return HttpResponse.noContent()
        return HttpResponse.ok(albums.map { item: Album -> item.toDTO() })
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