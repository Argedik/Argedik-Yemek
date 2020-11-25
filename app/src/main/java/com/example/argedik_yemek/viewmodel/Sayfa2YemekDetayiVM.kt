package com.example.argedik_yemek.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.argedik_yemek.model.Yemek

class Sayfa2YemekDetayiVM : ViewModel() {

    val besinLiveData = MutableLiveData<Yemek>()

    fun roomVerisiniAl(){
        val deneme= Yemek("deneme","malzeme","sosu","2. sos mu olur","boşver")
        besinLiveData.value= deneme
    }
}