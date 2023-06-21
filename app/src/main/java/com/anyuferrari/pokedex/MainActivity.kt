package com.anyuferrari.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.anyuferrari.pokedex.adapters.PokemonAdapter
import com.anyuferrari.pokedex.databinding.ActivityMainBinding
import com.anyuferrari.pokedex.responses.PokemonDetailsResponse
import com.anyuferrari.pokedex.services.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: PokemonAdapter

    companion object {
        const val PKMN_ID = "pkmn_id"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit = getRetrofit()
        initUi()
    }

    private fun initUi() {
        adapter = PokemonAdapter {}
        firstOnes()
        binding.rvPkmnCards.setHasFixedSize(true)
        binding.rvPkmnCards.layoutManager = GridLayoutManager(this, 2)
        binding.rvPkmnCards.adapter = adapter


    }

    private fun firstOnes() {
        Log.i("miercoles", "aaaa")
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(ApiService::class.java).getInitialPokemon()
            val responseList: MutableList<PokemonDetailsResponse> = ArrayList()

            if (myResponse.isSuccessful) {
                val itemResponse = myResponse.body()
                if (itemResponse != null) {
                    for (pkmn in itemResponse.results) {
                        val detailsResponse =
                            retrofit.create(ApiService::class.java).getPokemonDetails(pkmn.name)
                        if (detailsResponse.isSuccessful) {
                            val detResponse = detailsResponse.body()
                            if (detResponse != null) {
                                responseList.add(detResponse)
                            }
                        }
                    }
                    runOnUiThread {
                        adapter.updateDetails(responseList)
                    }

                }
            }
        }
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}