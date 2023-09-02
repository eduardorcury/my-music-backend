package com.erc.clients.spotify.autorizacao

import io.micronaut.http.HttpHeaders.AUTHORIZATION
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType.APPLICATION_FORM_URLENCODED
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client

@Client("\${spotify.accounts.url}")
interface AutorizadorClient {

    @Post(value = "/api/token")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_FORM_URLENCODED)
    fun gerarToken(grant_type: String = "client_credentials",
                   @Header(AUTHORIZATION) authorization: String): HttpResponse<AutorizadorResponse?>

    @Get(value = "/authorize")
    fun login(@QueryValue client_id: String,
              @QueryValue response_type: String,
              @QueryValue redirect_uri: String,
              @QueryValue state: String,
              @QueryValue scope: String): HttpResponse<Any>

    @Post(value = "/api/token")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_FORM_URLENCODED)
    fun exchange(grant_type: String = "authorization_code",
                 redirect_uri: String?,
                 code: String?,
                 @Header(AUTHORIZATION) authorization: String?): HttpResponse<AutorizadorResponse?>

}