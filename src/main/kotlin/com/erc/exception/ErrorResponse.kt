package com.erc.exception

import io.micronaut.http.HttpMethod
import java.net.URI

data class ErrorResponse(
        val codigo: Int,
        val metodo: HttpMethod,
        val uri: URI,
        val mensagem: String
)