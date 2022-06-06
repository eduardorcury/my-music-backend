package com.erc.services.autorizacao

import com.erc.clients.spotify.autorizacao.AutorizadorClient
import io.micronaut.context.annotation.Property
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.util.*

@Singleton
class AutorizadorService(
        @Inject val client: AutorizadorClient,
) {

    @field:Property(name = "spotify.credenciais.client_id")
    var clientId: String? = null

    @field:Property(name = "spotify.credenciais.client_secret")
    var clientSecret: String? = null

    fun gerarToken(): String? {
        val credenciais = "${clientId}:${clientSecret}".encodeToByteArray().let {
            Base64.getEncoder().encodeToString(it)
        }
        val token = client.gerarToken(authorization = "Basic $credenciais")
        return token.body()?.token
    }

}