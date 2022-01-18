package com.fh.pokemon.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fh.pokemon.data.model.PokemonInfo
import com.fh.pokemon.data.repo.PokemonInfoRepository
import kotlinx.coroutines.launch

class PokemonInfoViewModel @ViewModelInject constructor(private val repository: PokemonInfoRepository) :
    ViewModel() {

    private val _pokemonInfoLiveData = MutableLiveData<PokemonInfo>()

    val pokemonInfoLiveData: LiveData<PokemonInfo>
        get() = _pokemonInfoLiveData

    fun getPokemonInfo(name: String) = viewModelScope.launch {
        _pokemonInfoLiveData.value = repository.getPokemonInfo(name)
    }

}