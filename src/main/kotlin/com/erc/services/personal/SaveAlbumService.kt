package com.erc.services.personal

import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import aws.sdk.kotlin.services.dynamodb.model.PutItemRequest
import com.erc.clients.spotify.api.SpotifyApiClient
import io.micronaut.context.annotation.Property
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.runBlocking

@Singleton
class SaveAlbumService(
    @Inject val spotifyClient: SpotifyApiClient
) {

    @field:Property(name = "aws.dynamo_db.table_name")
    var table: String? = null

    @field:Property(name = "aws.dynamo_db.region")
    var dynamoRegion: String? = null

    fun saveAlbum(albumId: String, albumRating: String, token: String) {

        val userProfile = spotifyClient.userProfile(token)
            .body()
            ?.toDomain()
        val album = spotifyClient.getAlbum(albumId, token)
            .body()
            ?.toDomain()

        val itemValues = mutableMapOf<String, AttributeValue>()

        if (userProfile != null && album != null ) {
            itemValues["album_id"] = AttributeValue.S(albumId)
            itemValues["user_id"] = AttributeValue.S(userProfile.id)
            itemValues["rating"] = AttributeValue.S(albumRating)
            itemValues["name"] = AttributeValue.S(album.nome)
            itemValues["spotify_uri"] = AttributeValue.S(album.uriSpotify)
            itemValues["image_url"] = AttributeValue.S(album.urlImagem ?: "")
            itemValues["release_date"] = AttributeValue.S(album.dataDeLancamento)
            itemValues["artists"] = AttributeValue.L(album.artistas.map { artista -> AttributeValue.S(artista.nome) })
        }

        val saveAlbumRequest = PutItemRequest {
            tableName = table
            item = itemValues
        }

        DynamoDbClient { region = dynamoRegion }.use { ddb ->
            runBlocking {
                ddb.putItem(saveAlbumRequest)
            }
        }
    }

}