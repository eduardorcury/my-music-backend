package com.erc.exception

import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.server.exceptions.response.ErrorContext
import io.micronaut.http.server.exceptions.response.ErrorResponseProcessor
import jakarta.inject.Singleton

@Singleton
class ErrorProcessor() : ErrorResponseProcessor<ErrorResponse> {

    override fun processResponse(errorContext: ErrorContext, baseResponse: MutableHttpResponse<*>): MutableHttpResponse<ErrorResponse> {
        val erro = ErrorResponse(
                codigo = baseResponse.code(),
                metodo = errorContext.request.method,
                uri = errorContext.request.uri,
                mensagem = errorContext.errors[0].message
        )
        return baseResponse.body(erro)
    }

}