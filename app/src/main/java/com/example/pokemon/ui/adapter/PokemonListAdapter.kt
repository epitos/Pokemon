package com.example.pokemon.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.data.model.Result

class PokemonListAdapter(private val pokemonList: ArrayList<Result>):
    RecyclerView.Adapter<PokemonListAdapter.ViewHolder>()  {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTextView.text = pokemonList[position].name
    }

    override fun getItemCount() = pokemonList.size

    fun addData(list: List<Result>) {
        pokemonList.addAll(list)
    }
}