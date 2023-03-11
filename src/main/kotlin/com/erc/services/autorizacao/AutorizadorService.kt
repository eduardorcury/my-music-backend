package com.erc.services.autorizacao

import com.erc.clients.spotify.autorizacao.AutorizadorClient
import io.micronaut.context.annotation.Property
import io.micronaut.http.client.HttpClientConfiguration
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

@Singleton
class AutorizadorService(
        @Inject val client: AutorizadorClient,
        @Inject val clientConfiguration: HttpClientConfiguration
) {

    @field:Property(name = "spotify.credenciais.client_id")
    var clientId: String? = null

    @field:Property(name = "spotify.credenciais.client_secret")
    var clientSecret: String? = null

    val logger: Logger = LoggerFactory.getLogger(AutorizadorService::class.java)

    fun gerarToken(): String? {
        val credenciais = "${clientId}:${clientSecret}".encodeToByteArray().let {
            Base64.getEncoder().encodeToString(it)
        }
        logger.info("Connect Timeout: ${clientConfiguration.connectTimeout.get()}");
        logger.info("Read timeout: ${clientConfiguration.readTimeout.get()}");
        val token = client.gerarToken(authorization = "Basic $credenciais")
        return token.body()?.token
    }

}