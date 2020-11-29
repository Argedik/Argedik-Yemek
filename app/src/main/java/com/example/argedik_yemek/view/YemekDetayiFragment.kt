package com.example.argedik_yemek.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.argedik_yemek.R
import com.example.argedik_yemek.databinding.FragmentYemekDetayiBinding
import com.example.argedik_yemek.util.gorselIndir
import com.example.argedik_yemek.util.placeHolderYap
import com.example.argedik_yemek.viewmodel.Sayfa2YemekDetayiVM
import kotlinx.android.synthetic.main.fragment_yemek_detayi.*

class YemekDetayiFragment : Fragment() {

    private lateinit var viewModel : Sayfa2YemekDetayiVM
    private lateinit var dataBinding:FragmentYemekDetayiBinding

    private var yemekId=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_yemek_detayi,container,false)
        //return inflater.inflate(R.layout.fragment_yemek_detayi, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            yemekId = YemekDetayiFragmentArgs.fromBundle(it).YemekId
        }
        viewModel = ViewModelProviders.of(this).get(Sayfa2YemekDetayiVM::class.java)
        viewModel.roomVerisiniAl(yemekId)

        observerLivaData()
    }
    fun observerLivaData(){
        viewModel.yemekLiveData.observe(viewLifecycleOwner, Observer { yemek->
            yemek?.let{
                dataBinding.secilenYemek =it
                /*ilkmetin.text=it.aisim
                ikincimetin.text=it.amalzeme
                ucuncumetin.text=it.asos
                besincimetin.text=it.amalzeme
                context?.let {
                    liste_Detay_Resmi.gorselIndir(yemek.gorsel, placeHolderYap(it))
                }*/
            }
        })
    }
}