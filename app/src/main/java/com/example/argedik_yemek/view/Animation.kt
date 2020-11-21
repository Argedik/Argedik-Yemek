package com.example.argedik_yemek.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import com.airbnb.lottie.LottieAnimationView
import com.example.argedik_yemek.R
import com.example.argedik_yemek.menues.ui.menues
import java.util.*

class Animation: Activity() {
    var timer: Timer? = null
    //var bganim: Animation? = null
    var bgapp: ImageView? = null
    var kss_logo: ImageView?=null
    var animasyonlogo: LottieAnimationView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.animation)
        val logo = logo()
        logo.start()

    }

    private inner class logo : Thread() {
        override fun run() {
            try {
            bgapp = findViewById<View>(R.id.image) as ImageView
            kss_logo = findViewById<View>(R.id.app_logo) as ImageView
            animasyonlogo= findViewById(R.id.lottie) as LottieAnimationView

            animasyonlogo!!.animate().translationY(2800f).setDuration(6000).startDelay = 300
            bgapp!!.animate().translationY(-3200f).setDuration(2000).startDelay = 300
            kss_logo!!.animate().alpha(0f).setDuration(1000).startDelay = 600
            sleep(3200)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            val intent = Intent(this@Animation, menues::class.java)
            startActivity(intent)
            finish()
        }
    }

}