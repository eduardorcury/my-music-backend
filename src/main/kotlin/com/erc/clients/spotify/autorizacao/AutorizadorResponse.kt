package com.erc.clients.spotify.autorizacao

import com.fasterxml.jackson.annotation.JsonProperty

data class AutorizadorResponse(

        @JsonProperty("access_token")
        val token: String?,

        @JsonProperty("token_type")
        val tipo: String?,

        @JsonProperty("expires_in")
        val expiraEm: Int

)