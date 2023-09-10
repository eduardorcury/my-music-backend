package com.erc.clients.spotify.api

import com.erc.clients.spotify.api.dtos.AlbumPesquisaResponse
import com.erc.clients.spotify.api.dtos.AlbumResponse
import com.erc.clients.spotify.api.dtos.RecentlyPlayedResponse
import com.erc.clients.spotify.api.dtos.UserProfileResponse
import io.micronaut.http.HttpHeaders.AUTHORIZATION
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client

@Client("\${spotify.api.url}")
interface SpotifyApiClient {

    @Get("/search")
    @Consumes(MediaType.APPLICATION_JSON)
    fun buscaAlbum(@QueryValue("type") consultas: Array<String> = arrayOf("album"),
                   @QueryValue("limit") limite: Int = 10,
                   @QueryValue("q") pesquisa: String,
                   @Header(AUTHORIZATION) token: String): HttpResponse<AlbumPesquisaResponse>

    @Get("/me/player/recently-played")
    fun recentlyPlayed(@Header(AUTHORIZATION) token: String): HttpResponse<RecentlyPlayedResponse>

    @Get("/me")
    fun userProfile(@Header(AUTHORIZATION) token: String): HttpResponse<UserProfileResponse>

    @Get("/albums/{id}")
    fun getAlbum(@PathVariable id: String,
                 @Header(AUTHORIZATION) token: String): HttpResponse<AlbumResponse>

}