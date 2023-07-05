package com.anyuferrari.pokedex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.anyuferrari.pokedex.R
import com.anyuferrari.pokedex.diffUtils.PokemonDIffUtil
import com.anyuferrari.pokedex.responses.PokemonDetailsResponse
import com.anyuferrari.pokedex.viewHolders.PokemonViewHolder

class PokemonAdapter(
    private var pkmnDetailsList: List<PokemonDetailsResponse> = emptyList(),
    private val onItemSelected: (String) -> Unit
) : RecyclerView.Adapter<PokemonViewHolder>() {

    fun updateList(newList: List<PokemonDetailsResponse>){
        val pkmnDiff = PokemonDIffUtil(pkmnDetailsList, newList)
        val result = DiffUtil.calculateDiff(pkmnDiff)
        pkmnDetailsList = newList
        result.dispatchUpdatesTo(this)
    }
    fun updateDetails(pkmnDetailsList: List<PokemonDetailsResponse>) {
        this.pkmnDetailsList = pkmnDetailsList
        updateList(pkmnDetailsList)
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