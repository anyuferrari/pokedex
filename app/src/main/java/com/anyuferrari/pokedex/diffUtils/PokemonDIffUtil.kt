package com.anyuferrari.pokedex.diffUtils

import androidx.recyclerview.widget.DiffUtil
import com.anyuferrari.pokedex.responses.PokemonDetailsResponse

class PokemonDIffUtil(
    private val oldList: List<PokemonDetailsResponse>,
    private val newList: List<PokemonDetailsResponse>
): DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].pkmnId == newList[newItemPosition].pkmnId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}