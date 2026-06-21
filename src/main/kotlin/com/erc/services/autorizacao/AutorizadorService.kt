package com.erc.services.autorizacao

import com.erc.clients.spotify.autorizacao.AutorizadorClient
import io.micronaut.context.annotation.Property
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.net.URI
import java.util.*

@Singleton
class AutorizadorService(
        @Inject val client: AutorizadorClient
) {

    @field:Property(name = "spotify.credenciais.client_id")
    var clientId: String? = null

    @field:Property(name = "spotify.credenciais.client_secret")
    var clientSecret: String? = null

    @field:Property(name = "web.redirect_url")
    var redirectUrl: String? = null

    @field:Property(name = "web.local_redirect_url")
    var localRedirectUrl: String? = null

    @field:Property(name = "spotify.scopes")
    var scopes: List<String> = emptyList()

    private fun resolveRedirectUrl(origin: String?) =
        if (origin?.contains("localhost") == true || origin?.contains("127.0.0.1") == true)
            localRedirectUrl ?: redirectUrl ?: ""
        else
            redirectUrl ?: ""

    fun gerarToken(): String? {
        val token = client.gerarToken(authorization = "Basic ${encodeCredentials()}")
        return token.body()?.token
    }

    fun login(origin: String?): URI? {
        val escopos = scopes.reduce { result, scope -> "$result $scope" }
        val login = client.login(
            client_id = clientId ?: "",
            response_type = "code",
            redirect_uri = resolveRedirectUrl(origin),
            state = UUID.randomUUID().toString(),
            scope = escopos
        )
        return login.headers["location"]?.let { URI.create(it) }
    }

    fun exchange(code: String, origin: String?): String? {
        val token = client.exchange(authorization = "Basic ${encodeCredentials()}", code = code, redirect_uri = resolveRedirectUrl(origin))
        return token.body()?.token
    }

    private fun encodeCredentials(): String {
        return "${clientId}:${clientSecret}".encodeToByteArray().let {
            Base64.getEncoder().encodeToString(it)
        }
    }

}