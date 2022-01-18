package com.fh.pokemon.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class PokemonList(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Result>
):Parcelable