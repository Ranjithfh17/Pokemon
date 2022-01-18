package com.fh.pokemon.data.repo

import com.fh.pokemon.api.PokemonApi
import javax.inject.Inject

class PokemonInfoRepository @Inject constructor(private val pokemonApi: PokemonApi) {

    suspend fun getPokemonInfo(name:String)=pokemonApi.getPokemonInfo(name)

}