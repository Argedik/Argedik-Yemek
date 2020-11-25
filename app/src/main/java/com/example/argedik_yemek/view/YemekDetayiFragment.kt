package com.example.argedik_yemek.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.argedik_yemek.R
import com.example.argedik_yemek.viewmodel.Sayfa2YemekDetayiVM
import kotlinx.android.synthetic.main.fragment_yemek_detayi.*

class YemekDetayiFragment : Fragment() {

    private lateinit var viewModel : Sayfa2YemekDetayiVM


    private var yemekId=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_yemek_detayi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(Sayfa2YemekDetayiVM::class.java)
        viewModel.roomVerisiniAl()



        arguments?.let {
            yemekId = YemekDetayiFragmentArgs.fromBundle(it).YemekId
            println(yemekId)
        }
        observerLivaData()
    }
    fun observerLivaData(){
        viewModel.besinLiveData.observe(viewLifecycleOwner, Observer {yemek->
            yemek?.let{
                ilkmetin.text=it.aisim
                ikincimetin.text=it.amalzeme
                ucuncumetin.text=it.asos
                besincimetin.text=it.amalzeme
            }
        })
    }
}