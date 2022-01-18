package com.fh.pokemon.callbacks

import com.fh.pokemon.data.model.PokemonList
import com.fh.pokemon.data.model.Result

interface PokemonListener {
    fun onPokemonClick(pokemonList:Result)
}