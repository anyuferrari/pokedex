package com.anyuferrari.pokedex.services

import com.anyuferrari.pokedex.responses.PokemonDataResponse
import com.anyuferrari.pokedex.responses.PokemonDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("pokemon?limit=20&offset=0")
    suspend fun getInitialPokemon(): Response<PokemonDataResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(@Path("name") name: String): Response<PokemonDetailsResponse>
}
