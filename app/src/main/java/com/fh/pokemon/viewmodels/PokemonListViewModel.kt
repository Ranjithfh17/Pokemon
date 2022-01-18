package com.fh.pokemon.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.fh.pokemon.data.repo.PokemonListRepository

class PokemonListViewModel @ViewModelInject constructor(private val repository: PokemonListRepository) :
    ViewModel() {

    val pokemonList = repository.getPokemonList()

}


