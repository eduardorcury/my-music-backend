package com.erc.services.pesquisa

import com.erc.services.autorizacao.AutorizadorService
import com.erc.clients.spotify.api.SpotifyApiClient
import com.erc.dominio.Album
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class PesquisaService(
        @Inject val spotifyClient: SpotifyApiClient,
        @Inject val autorizadorService: AutorizadorService
) {

    fun pesquisaPorAlbum(texto : String) : List<Album>? {
        val token: String = autorizadorService.gerarToken() ?: throw HttpStatusException(HttpStatus.UNAUTHORIZED, "Erro ao buscar token")
        val albuns = spotifyClient.buscaAlbum(pesquisa = texto, token = "Bearer $token")
        return albuns.body()
                ?.itens
                ?.albums
                ?.map { itens -> itens.toDomain() }
                ?.toList()
    }

}