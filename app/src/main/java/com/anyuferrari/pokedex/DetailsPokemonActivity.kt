package com.anyuferrari.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.anyuferrari.pokedex.adapters.TypesAdapter
import com.anyuferrari.pokedex.databinding.ActivityPokemonDetailsBinding
import com.anyuferrari.pokedex.services.ApiService
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsPokemonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonDetailsBinding
    private lateinit var retrofit: Retrofit

    companion object {
        const val PKMN_ID = "pkmnId"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        val id = intent.getStringExtra(PKMN_ID).orEmpty()
        Log.i("pokemonDetails", "onCreate $id")
        initUi(id)
    }

    private fun initUi(pkmnId: String) {
        val cont = this
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(ApiService::class.java).getPokemonDetails(pkmnId)
            if (myResponse.isSuccessful) {
                val itemResponse = myResponse.body()
                runOnUiThread {
                    if (itemResponse != null) {
                        val adapter = TypesAdapter(itemResponse.types)
                        binding.rvTypes.setHasFixedSize(true)
                        binding.rvTypes.layoutManager = LinearLayoutManager(cont, LinearLayoutManager.HORIZONTAL, false)
                        binding.rvTypes.adapter = adapter
                        binding.tvName.text = itemResponse.name.replaceFirstChar { it.titlecase() }
                        val number = String.format("%03d", itemResponse.pkmnId.toInt())
                        binding.tvId.text = number
                        binding.tvHeight.text = itemResponse.height
                        binding.tvWeight.text = itemResponse.weight
                        Picasso.get().load(itemResponse.sprites.frontUrl).into(binding.ivPkmnFront)
                        Picasso.get().load(itemResponse.sprites.backUrl).into(binding.ivPkmnBack)
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