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
    @SerializedName("sprites") val sprites: PokemonSpritesResponse

)


data class PokemonSpritesResponse(
    @SerializedName("front_default") val frontUrl: String,
    @SerializedName("back_default") val backUrl: String
)
