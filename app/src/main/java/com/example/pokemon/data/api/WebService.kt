package com.example.pokemon.data.api


import com.example.artwork.utils.Const.POKEMON
import retrofit2.Response
import retrofit2.http.GET

interface WebService {

    @GET(POKEMON)
    suspend fun getPokemonList(): Response<com.example.pokemon.data.model.Response>
}