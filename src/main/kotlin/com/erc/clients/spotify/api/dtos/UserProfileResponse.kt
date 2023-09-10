package com.erc.clients.spotify.api.dtos

import com.erc.dominio.UserProfile
import com.fasterxml.jackson.annotation.JsonProperty

data class UserProfileResponse(
    @JsonProperty("country")
    val country: String?,

    @JsonProperty("display_name")
    val displayName: String,

    @JsonProperty("email")
    val email: String,

    @JsonProperty("explicit_content")
    val explicitContent: ExplicitContent?,

    @JsonProperty("external_urls")
    val externalUrls: ExternalUrls?,

    @JsonProperty("followers")
    val followers: Followers?,

    @JsonProperty("href")
    val href: String?,

    @JsonProperty("id")
    val id: String,

    @JsonProperty("images")
    val images: List<ImageResponse>,

    @JsonProperty("product")
    val product: String?,

    @JsonProperty("type")
    val type: String?,

    @JsonProperty("uri")
    val uri: String?

) {
    fun toDomain(): UserProfile {
        return UserProfile(
            email = this.email,
            id = this.id,
            displayName = this.displayName
        )
    }
}

data class ExplicitContent(
    @JsonProperty("filter_enabled")
    val filterEnabled: Boolean,

    @JsonProperty("filter_locked")
    val filterLocked: Boolean
)

data class Followers(
    @JsonProperty("href")
    val href: String?,

    @JsonProperty("total")
    val total: Int
)
