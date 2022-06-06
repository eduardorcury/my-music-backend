package com.erc.exception

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.http.server.exceptions.ExceptionHandler
import io.micronaut.http.server.exceptions.response.ErrorContext
import jakarta.inject.Singleton

@Singleton
class HttpClientExceptionHandler(
        private val errorProcessor: ErrorProcessor
) : ExceptionHandler<HttpClientResponseException, HttpResponse<ErrorResponse>> {

    override fun handle(request: HttpRequest<*>, exception: HttpClientResponseException): HttpResponse<ErrorResponse> {
        return errorProcessor.processResponse(
                ErrorContext.builder(request)
                        .cause(exception)
                        .error { exception.message }
                        .build(),
                HttpResponse.status<Any>(exception.status)
        )
    }

}