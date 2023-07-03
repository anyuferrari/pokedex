package com.anyuferrari.pokedex.services

import com.anyuferrari.pokedex.responses.PokemonDataResponse
import com.anyuferrari.pokedex.responses.PokemonDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    suspend fun getInitialPokemon(
        @Query("limit") limit: String,
        @Query("offset") offset: String
    ): Response<PokemonDataResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(@Path("name") name: String): Response<PokemonDetailsResponse>
}
