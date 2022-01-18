package com.fh.pokemon.ui.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.fh.pokemon.R
import com.fh.pokemon.data.model.Type
import com.fh.pokemon.databinding.FragmentDetailBinding
import com.fh.pokemon.util.ColorUtil
import com.fh.pokemon.util.ColorUtil.createDetailColorPalette
import com.fh.pokemon.viewmodels.PokemonInfoViewModel
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.skydoves.rainbow.Rainbow
import com.skydoves.rainbow.RainbowOrientation
import com.skydoves.rainbow.color
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import java.util.Collections.list
import kotlin.collections.ArrayList

@AndroidEntryPoint
class Detail : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel by viewModels<PokemonInfoViewModel>()

    private val args by navArgs<DetailArgs>()
    private var list:List<Type> = ArrayList()


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        Log.i("TAG", "onViewCreated: ${args.info.name}")

        getPokemonInfo()

        binding.backArrow.setOnClickListener {
            findNavController().popBackStack()
        }

        setBaseStats()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun getPokemonInfo() {
        viewModel.getPokemonInfo(args.info.name)

        viewModel.pokemonInfoLiveData.observe(viewLifecycleOwner) { info ->
            binding.apply {
               createDetailColorPalette(
                    mainLayout,
                    args.info.getImageUrl(),
                    pokemonImage,
                )

                pokemonId.text=info.id.toString()
                pokemonName.text = info.name
                weight.text = info.weight.toString()
                height.text = info.height.toString()
                list=info.types

                for (type in info.types){
                    typeOne.text=type.type.name
                    typeTwo.text=type.slot.toString()
                }

            }
        }

    }

    private fun setBaseStats(){
        binding.apply {
            hpProgress.progress=30
            hpText.text="30/100"
        }
    }



}