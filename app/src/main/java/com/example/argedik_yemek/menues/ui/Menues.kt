package com.example.argedik_yemek.menues.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.argedik_yemek.R
import com.example.argedik_yemek.view.Animation
import com.example.argedik_yemek.view.Giris_Sayfasi
import com.example.argedik_yemek.view.Sayfa1

class menues : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menues)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    fun deneme(view:View){
        val intent = Intent(this@menues, Giris_Sayfasi::class.java)
        startActivity(intent)
        finish()
    }
    fun top10(view:View){
        val intent = Intent(this@menues, Sayfa1::class.java)
        startActivity(intent)
    }
}