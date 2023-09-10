package com.erc.services.personal

import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import aws.sdk.kotlin.services.dynamodb.model.QueryRequest
import com.erc.clients.spotify.api.SpotifyApiClient
import com.erc.dominio.Album
import com.erc.dominio.Artista
import io.micronaut.context.annotation.Property
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.runBlocking

@Singleton
class GetAlbumService(
    @Inject val spotifyApiClient: SpotifyApiClient
) {

    @field:Property(name = "aws.dynamo_db.table_name")
    var table: String? = null

    @field:Property(name = "aws.dynamo_db.region")
    var dynamoRegion: String? = null

    fun getAlbums(token: String): List<Album> {

        val userProfile = spotifyApiClient.userProfile(token)
            .body()
            ?.toDomain()

        val queryRequest = QueryRequest {
            tableName = table
            indexName = "user_id_index"
            keyConditionExpression = "user_id = :user_id_value"
            expressionAttributeValues = mapOf(Pair(":user_id_value", AttributeValue.S(userProfile?.id ?: "")))
        }

        val queryResponse = DynamoDbClient { region = dynamoRegion }.use { ddb ->
            runBlocking {
                ddb.query(queryRequest)
            }
        }

        return queryResponse
            .items?.map {album ->
                Album(
                    nome = album["name"]?.asS() ?: "",
                    uriSpotify = album["spotify_uri"]?.asS() ?: "",
                    urlImagem = album["image_url"]?.asS() ?: "",
                    id = album["album_id"]?.asS() ?: "",
                    artistas = album["artists"]?.asL()?.map { artist -> Artista(artist.asS())  } ?: emptyList(),
                    dataDeLancamento = album["release_date"]?.asS() ?: "",
                    nota = album["rating"]?.asS()
                )
        } ?: emptyList()
    }

}