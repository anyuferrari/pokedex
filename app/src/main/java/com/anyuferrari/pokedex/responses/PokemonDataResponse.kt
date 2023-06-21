package com.anyuferrari.pokedex.responses

import com.google.gson.annotations.SerializedName

data class PokemonDataResponse(
    @SerializedName("results") val results: List<PokemonItemResponse>
)

data class PokemonItemResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val pkmnUrl: String
)

data class PokemonDetailsResponse(
    @SerializedName("id") val pkmnId: String,
    @SerializedName("name") val name: String,
    @SerializedName("sprites") val sprites: PokemonSpritesResponse,
    @SerializedName("height") val height: String,
    @SerializedName("weight") val weight: String,
    @SerializedName("types") val types: List<TypesResponse>

)


data class PokemonSpritesResponse(
    @SerializedName("front_default") val frontUrl: String,
    @SerializedName("back_default") val backUrl: String
)

data class TypesResponse(
    @SerializedName("type") val type: Type
)

data class Type(
    @SerializedName("name") val typename: String
)