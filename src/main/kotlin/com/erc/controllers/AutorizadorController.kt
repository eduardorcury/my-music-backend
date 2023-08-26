package com.erc.controllers

import com.erc.services.autorizacao.AutorizadorService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import jakarta.inject.Inject

@Controller
class AutorizadorController(
        @Inject val service: AutorizadorService
) {

    @Get("/token")
    fun gerarToken(): HttpResponse<String> {
        return HttpResponse.ok(service.gerarToken())
    }

    @Get("/login")
    fun login(): HttpResponse<String> {
        return HttpResponse.redirect(service.login())
    }

}