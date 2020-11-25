package com.example.argedik_yemek.menues.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.argedik_yemek.R
import com.example.argedik_yemek.menues.ui.notifications.NotificationsFragmentDirections
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_yemek_detayi.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Yemek_Sayfasi.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToYemekListesiFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

}