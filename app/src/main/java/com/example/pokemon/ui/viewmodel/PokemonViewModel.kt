package com.example.pokemon.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.data.model.Result
import com.example.pokemon.data.repository.PokemonRepository
import com.example.pokemon.utils.Resource
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel() {

    private val repository = PokemonRepository()

    private val _pokemonList = MutableLiveData<Resource<List<Result>>>()
    val pokemonList: LiveData<Resource<List<Result>>>
        get() = _pokemonList

    init {
        viewModelScope.launch {
            _pokemonList.postValue(Resource.loading(null))
            repository.getPokemonList().let {
                if (it.isSuccessful) {
                    _pokemonList.postValue(Resource.success(it.body()!!.results))
                } else {
                    _pokemonList.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        }
    }
}