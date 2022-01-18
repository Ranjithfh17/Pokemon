package com.fh.pokemon.ui.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fh.pokemon.R
import com.fh.pokemon.callbacks.PokemonListener
import com.fh.pokemon.data.model.Result
import com.fh.pokemon.databinding.AdapterPokemonListBinding
import com.fh.pokemon.util.ColorUtil

class PokemonListAdapter(private val pokemonListener: PokemonListener) :
    PagingDataAdapter<Result, PokemonListAdapter.PokemonListViewHolder>(DIFFER) {

    var defaultColor = R.color.white
    var bitmap: Bitmap? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val binding =
            AdapterPokemonListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonListViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        val pokemonList = getItem(position)
        if (pokemonList != null) {
            holder.bind(pokemonList)
        }
    }


    inner class PokemonListViewHolder(private val binding: AdapterPokemonListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        pokemonListener.onPokemonClick(item)
                    }
                }
            }
        }

        fun bind(result: Result) {
            binding.apply {
                Glide.with(itemView)
                    .load(result.getImageUrl())
                    .into(pokemonImage)

                pokemonName.text = result.name

                ColorUtil.createPalette(cardView, result.getImageUrl(), pokemonImage)


            }


        }
    }


    companion object {
        private val DIFFER = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result) =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Result, newItem: Result) =
                oldItem == newItem
        }


    }
}