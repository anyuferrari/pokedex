package com.anyuferrari.pokedex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anyuferrari.pokedex.R
import com.anyuferrari.pokedex.responses.TypesResponse
import com.anyuferrari.pokedex.viewHolders.TypesViewHolder

class TypesAdapter(
    private var pkmnTypesList: List<TypesResponse> = emptyList()
) : RecyclerView.Adapter<TypesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypesViewHolder {
        return TypesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_type, parent, false)
        )
    }

    override fun getItemCount() = pkmnTypesList.size

    override fun onBindViewHolder(viewHolder: TypesViewHolder, position: Int) {
        viewHolder.bind(pkmnTypesList[position].type.typename)
    }

}