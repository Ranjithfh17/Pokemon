package com.fh.pokemon.util

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.skydoves.rainbow.Rainbow
import com.skydoves.rainbow.RainbowOrientation
import com.skydoves.rainbow.color

object ColorUtil {

    @SuppressLint("CheckResult")
    fun createPalette(view: CardView, url: String, imageView: ImageView) {
        val context = view.context

        Glide.with(context)
            .load(url)
            .listener(
                GlidePalette.with(url)
                    .use(BitmapPalette.Profile.MUTED_LIGHT)
                    .intoCallBack { palette ->
                        val light = palette?.dominantSwatch?.rgb
                        if (light != null) {
                            view.setCardBackgroundColor(light)
                        }
                    }.crossfade(true)

            ).into(imageView)

    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("CheckResult")
    fun createDetailColorPalette(view: View, url: String, imageView: ImageView) {
        val context = view.context

        Glide.with(context)
            .load(url)
            .listener(
                GlidePalette.with(url)
                    .use(BitmapPalette.Profile.MUTED_LIGHT)
                    .intoCallBack { palette ->
                        val light = palette?.lightVibrantSwatch?.rgb
                        val domain = palette?.dominantSwatch?.rgb

                        if (domain != null) {
                            if (light != null) {
                                Rainbow(view).palette {
                                    +color(domain)
                                    +color(light)
                                }.background(orientation = RainbowOrientation.TOP_BOTTOM)
                            } else {
                                view.setBackgroundColor(domain)

                            }
                            if (context is AppCompatActivity) {
                                context.window.apply {
                                    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                                    statusBarColor = domain
                                }
                            }

                        }

                    }.crossfade(true)
            ).into(imageView)
    }
}