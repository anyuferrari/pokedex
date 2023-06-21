package com.anyuferrari.pokedex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anyuferrari.pokedex.R
import com.anyuferrari.pokedex.responses.PokemonDetailsResponse
import com.anyuferrari.pokedex.viewHolders.PokemonViewHolder

class PokemonAdapter(
    private var pkmnDetailsList: List<PokemonDetailsResponse> = emptyList(),
    private val onItemSelected: (String) -> Unit
) : RecyclerView.Adapter<PokemonViewHolder>() {

    fun updateDetails(pkmnDetailsList: List<PokemonDetailsResponse>) {
        this.pkmnDetailsList = pkmnDetailsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pkmn, parent, false)
        )
    }

    override fun getItemCount() = pkmnDetailsList.size

    override fun onBindViewHolder(viewHolder: PokemonViewHolder, position: Int) {
        viewHolder.bind(pkmnDetailsList[position], onItemSelected)
    }

}