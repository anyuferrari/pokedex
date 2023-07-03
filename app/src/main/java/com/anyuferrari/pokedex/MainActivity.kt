package com.anyuferrari.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.anyuferrari.pokedex.DetailsPokemonActivity.Companion.PKMN_ID
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUi()
    }

    private fun initUi() {

        val spinnerItems: Array<String> = resources.getStringArray(R.array.spinner_items)
        val spinnerAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.svPkmn.adapter = spinnerAdapter
        binding.svPkmn.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                update(p2)

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                firstOnes()
            }
        }
        adapter = PokemonAdapter { navigateToDetails(it) }
        binding.rvPkmnCards.setHasFixedSize(true)
        binding.rvPkmnCards.layoutManager = GridLayoutManager(this, 2)
        binding.rvPkmnCards.adapter = adapter


    }

    private fun firstOnes() {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(ApiService::class.java)
                .getInitialPokemon(limit = "151", offset = "0")
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
                        binding.progressBar.isVisible = false
                    }

                }
            }
        }
    }

    private fun update(gen: Int) {
        val limit: String
        val offset: String
        when (gen){
            0 -> {limit = "151"
            offset = "0"}
            1 -> {limit = "100"
            offset = "151"}
            2 -> {limit = "135"
            offset = "251"}
            3 -> {limit = "107"
            offset = "386"}
            4 -> {limit = "156"
            offset = "493"}
            5 -> {limit = "72"
            offset = "649"}
            6 -> {limit = "88"
            offset = "721"}
            7 -> {limit = "96"
            offset = "809"}
            else -> {
                limit = "20"
                offset = "20"
            }
        }
        binding.progressBar.isVisible = true
        binding.glPkmn.isVisible = false
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(ApiService::class.java)
                .getInitialPokemon(limit = limit, offset = offset)
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
                        binding.progressBar.isVisible = false
                        binding.glPkmn.isVisible = true
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

    private fun navigateToDetails(pkmnId: String) {
        val intent = Intent(this, DetailsPokemonActivity::class.java)
        intent.putExtra(PKMN_ID, pkmnId)
        startActivity(intent)
    }
}