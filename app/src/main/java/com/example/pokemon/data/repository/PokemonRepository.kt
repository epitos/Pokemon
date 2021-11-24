package com.example.pokemon.data.repository

import com.example.artwork.data.api.WebClient
import com.example.pokemon.data.api.WebService

class PokemonRepository {
    private val webService = WebClient.getClient().create(WebService::class.java)

    suspend fun getPokemonList() = webService.getPokemonList()
}
