package com.erc.controllers

import com.erc.controllers.dtos.TokenDTO
import com.erc.services.autorizacao.AutorizadorService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.QueryValue
import jakarta.inject.Inject
import java.net.URI

@Controller
class AutorizadorController(
        @Inject val service: AutorizadorService
) {

    @Get("/token")
    fun gerarToken(@QueryValue code: String, @Header("Origin") origin: String?): HttpResponse<TokenDTO> {
        return HttpResponse.ok(TokenDTO(service.exchange(code, origin)))
    }

    @Get("/login")
    fun login(@Header("Origin") origin: String?): HttpResponse<URI> {
        return HttpResponse.ok(service.login(origin))
    }

}