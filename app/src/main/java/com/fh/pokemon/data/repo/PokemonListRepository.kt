package com.fh.pokemon.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.fh.pokemon.api.PokemonApi
import com.fh.pokemon.data.model.Result
import com.fh.pokemon.data.paging.PokemonPagingSource
import com.fh.pokemon.util.Resources
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonListRepository @Inject constructor(private val pokemonApi: PokemonApi) {

     fun getPokemonList():Flow<PagingData<Result>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = true
        ),
        pagingSourceFactory = {PokemonPagingSource(pokemonApi)}
    ).flow


}