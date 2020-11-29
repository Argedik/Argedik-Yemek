package com.example.argedik_yemek.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.argedik_yemek.R
import com.example.argedik_yemek.adapter.RecyclerAdapter
import com.example.argedik_yemek.adapter.YemekRecyclerAdapter
import com.example.argedik_yemek.viewmodel.Sayfa1ViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_yemek_detayi.*
import kotlinx.android.synthetic.main.fragment_yemek_listesi.*


class YemekListesiFragment : Fragment() {

    private lateinit var viewModel1 : Sayfa1ViewModel
    private val recyclerYemekAdapter = YemekRecyclerAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_yemek_listesi, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*Yemek_listesi_button.setOnClickListener {
            val action = YemekListesiFragmentDirections.actionYemekListesiFragment3ToYemekDetayiFragment()
            Navigation.findNavController(it).navigate(action)
        }*/
        viewModel1 = ViewModelProviders.of(this).get(Sayfa1ViewModel::class.java)
        viewModel1.refreshData()

        yemekListesiRecycler.layoutManager=LinearLayoutManager(context)
        yemekListesiRecycler.adapter=recyclerYemekAdapter

        swipeRefreshLayout.setOnRefreshListener {
            yemekYukleniyor.visibility=View.VISIBLE
            yemekHataMesaji.visibility=View.GONE
            yemekListesiRecycler.visibility=View.GONE
            viewModel1.refreshFromInternet()
            //viewModel1.refreshData()
            swipeRefreshLayout.isRefreshing=false
        }

        observeLiveData()
    }

    fun observeLiveData(){
        viewModel1.yemekler.observe(viewLifecycleOwner, Observer { yemekler->
            yemekler?.let {
                yemekListesiRecycler.visibility=View.VISIBLE
                recyclerYemekAdapter.YemekListesiGuncelle(yemekler)

            }
        })
        viewModel1.yemekHataMesajı.observe(viewLifecycleOwner, Observer {hata->
            hata?.let {
                if(it){
                    yemekHataMesaji.visibility=View.VISIBLE
                    yemekListesiRecycler.visibility=View.GONE
                }else{
                    yemekHataMesaji.visibility=View.GONE
                }
            }
        })

        viewModel1.yemekYukleniyor.observe(viewLifecycleOwner, Observer {yukleniyor->
            yukleniyor?.let {
                if(it){
                    yemekListesiRecycler.visibility=View.GONE
                    yemekHataMesaji.visibility=View.GONE
                    yemekYukleniyor.visibility=View.VISIBLE
                }else{
                    yemekYukleniyor.visibility=View.GONE
                }
            }
        })
    }
}