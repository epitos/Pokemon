package com.example.pokemon.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.R
import com.example.pokemon.data.model.Result
import com.example.pokemon.ui.adapter.PokemonListAdapter
import com.example.pokemon.ui.viewmodel.PokemonViewModel
import com.example.pokemon.utils.Status
import kotlinx.android.synthetic.main.activity_pokemon.*

class PokemonActivity : AppCompatActivity() {

    private lateinit var artworksViewModel: PokemonViewModel
    private lateinit var adapter: PokemonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = PokemonListAdapter(arrayListOf())
        recyclerview.addItemDecoration(
            DividerItemDecoration(
                recyclerview.context,
                (recyclerview.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerview.adapter = adapter
    }

    private fun setupObserver() {
        artworksViewModel = ViewModelProvider(this)[PokemonViewModel::class.java]
        artworksViewModel.pokemonList.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    it.data?.let { pokemonList -> renderList(pokemonList) }
                    recyclerview.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progress_bar.visibility = View.VISIBLE
                    recyclerview.visibility = View.GONE
                }
                Status.ERROR -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: List<Result>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }
}