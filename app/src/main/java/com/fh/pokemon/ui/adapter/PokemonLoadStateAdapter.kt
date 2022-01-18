package com.fh.pokemon.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fh.pokemon.databinding.AdapterLoadStateBinding

class PokemonLoadStateAdapter(private  val retry:() -> Unit):
    LoadStateAdapter<PokemonLoadStateAdapter.PokemonLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PokemonLoadStateViewHolder {
        val binding =
            AdapterLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonLoadStateViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PokemonLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }


    inner class PokemonLoadStateViewHolder(private val binding: AdapterLoadStateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryBtn.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                noResult.isVisible= loadState !is LoadState.Loading
                retryBtn.isVisible=loadState !is LoadState.Loading
            }

        }

    }
}