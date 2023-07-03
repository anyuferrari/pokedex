package com.anyuferrari.pokedex.viewHolders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.anyuferrari.pokedex.R
import com.anyuferrari.pokedex.databinding.ItemTypeBinding

class TypesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemTypeBinding.bind(view)

    fun bind(
        type: String
    ){
        binding.root.setCardBackgroundColor( when (type) {
            "bug" -> ContextCompat.getColor(binding.root.context, R.color.fire)
            "dark" -> ContextCompat.getColor(binding.root.context, R.color.dark)
            "dragon" -> ContextCompat.getColor(binding.root.context, R.color.dragon)
            "electric" -> ContextCompat.getColor(binding.root.context, R.color.electric)
            "fairy" -> ContextCompat.getColor(binding.root.context, R.color.fairy)
            "fighting" -> ContextCompat.getColor(binding.root.context, R.color.fighting)
            "fire" -> ContextCompat.getColor(binding.root.context, R.color.fire)
            "flying" -> ContextCompat.getColor(binding.root.context, R.color.flying)
            "ghost" -> ContextCompat.getColor(binding.root.context, R.color.ghost)
            "grass" -> ContextCompat.getColor(binding.root.context, R.color.grass)
            "ground" -> ContextCompat.getColor(binding.root.context, R.color.ground)
            "ice" -> ContextCompat.getColor(binding.root.context, R.color.ice)
            "normal" -> ContextCompat.getColor(binding.root.context, R.color.normal)
            "poison" -> ContextCompat.getColor(binding.root.context, R.color.poison)
            "psychic" -> ContextCompat.getColor(binding.root.context, R.color.psychic)
            "rock" -> ContextCompat.getColor(binding.root.context, R.color.rock)
            "steel" -> ContextCompat.getColor(binding.root.context, R.color.steel)
            "water" -> ContextCompat.getColor(binding.root.context, R.color.water)
            "unknown" -> ContextCompat.getColor(binding.root.context, R.color.unknown)
            else -> ContextCompat.getColor(binding.root.context, R.color.ice)
        })
        binding.tvType.text = type.replaceFirstChar { it.titlecase() }
    }

}