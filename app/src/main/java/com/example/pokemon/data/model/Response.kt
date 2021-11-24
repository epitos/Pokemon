package com.example.pokemon.data.model

data class Response(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)