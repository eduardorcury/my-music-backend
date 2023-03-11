package com.erc.clients.spotify.autorizacao

import io.micronaut.core.annotation.Blocking
import io.micronaut.http.HttpHeaders.AUTHORIZATION
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType.APPLICATION_FORM_URLENCODED
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import io.micronaut.http.client.annotation.Client

@Client("\${spotify.accounts.url}")
interface AutorizadorClient {

    @Post(value = "/api/token")
    @Blocking
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_FORM_URLENCODED)
    fun gerarToken(grant_type: String? = "client_credentials",
                   @Header(AUTHORIZATION) authorization: String): HttpResponse<AutorizadorResponse?>

}