package com.fh.pokemon.data.paging

import androidx.paging.PagingSource
import com.fh.pokemon.api.PokemonApi
import com.fh.pokemon.data.model.PokemonList
import com.fh.pokemon.data.model.Result
import com.fh.pokemon.util.Constants.STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException

class PokemonPagingSource(
    private val pokemonApi: PokemonApi
) : PagingSource<Int, Result>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val pokemonList = pokemonApi.getPokemonList(position, params.loadSize)
            val result = pokemonList.results

            LoadResult.Page(
                result,
                if (position == STARTING_PAGE_INDEX) null else position - 1,
                if (result.isEmpty()) null else position + 1
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }
}