package com.anyuferrari.pokedex.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.anyuferrari.pokedex.databinding.ItemPkmnBinding
import com.anyuferrari.pokedex.responses.PokemonDetailsResponse
import com.squareup.picasso.Picasso

class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemPkmnBinding.bind(view)

    fun bind(
        pkmDetailsResponse: PokemonDetailsResponse,
        onItemSelected: (String) -> Unit
    ) {
        binding.tvPkmnName.text = pkmDetailsResponse.name.replaceFirstChar { it.titlecase() }
        Picasso.get().load(pkmDetailsResponse.sprites.frontUrl).into(binding.ivPkmn)
        binding.root.setOnClickListener{
            onItemSelected(pkmDetailsResponse.pkmnId)
        }
    }
}