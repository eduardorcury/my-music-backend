package com.erc.clients.spotify.api

import io.micronaut.core.annotation.Blocking
import io.micronaut.http.HttpHeaders.AUTHORIZATION
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client

@Client("\${spotify.api.url}")
interface SpotifyApiClient {

    @Get("/search")
    @Blocking
    @Consumes(MediaType.APPLICATION_JSON)
    fun buscaAlbum(@QueryValue("type") consultas: Array<String> = arrayOf("album"),
                   @QueryValue("limit") limite: Int = 10,
                   @QueryValue("q") pesquisa: String,
                   @Header(AUTHORIZATION) token: String) : HttpResponse<AlbumPesquisaResponse>

}