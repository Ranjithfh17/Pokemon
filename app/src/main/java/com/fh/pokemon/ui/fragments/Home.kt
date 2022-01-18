package com.fh.pokemon.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fh.pokemon.R
import com.fh.pokemon.callbacks.PokemonListener
import com.fh.pokemon.data.model.Result
import com.fh.pokemon.databinding.FragmentHomeBinding
import com.fh.pokemon.ui.adapter.PokemonListAdapter
import com.fh.pokemon.ui.adapter.PokemonLoadStateAdapter
import com.fh.pokemon.viewmodels.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class Home : Fragment(R.layout.fragment_home), PokemonListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var pokemonListAdapter: PokemonListAdapter
    private val viewModel by viewModels<PokemonListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        setUpRecyclerView()
        getPokemonList()
        setLoadState()
    }

    private fun setUpRecyclerView() {
        pokemonListAdapter = PokemonListAdapter(this)
        binding.recyclerView.apply {
           adapter = pokemonListAdapter.withLoadStateFooter(
                footer = PokemonLoadStateAdapter { pokemonListAdapter.retry() }
            )

            val gridLayoutManager=GridLayoutManager(context,2)
            gridLayoutManager.spanSizeLookup=object :GridLayoutManager.SpanSizeLookup(){
                override fun getSpanSize(position: Int): Int {
                    return if(position == pokemonListAdapter.itemCount && pokemonListAdapter.itemCount > 0){
                        2
                    }else{
                        1
                    }
                }
            }
           layoutManager =gridLayoutManager
        }
    }


    private fun getPokemonList() {

        lifecycleScope.launchWhenStarted {
            viewModel.pokemonList.collect {
                pokemonListAdapter.submitData(it)
            }
        }
    }


    private fun setLoadState() {
        pokemonListAdapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            }
        }

    }

    override fun onPokemonClick(pokemonList: Result) {
        val action = HomeDirections.actionHome2ToDetail(pokemonList)
        findNavController().navigate(action)
    }
}