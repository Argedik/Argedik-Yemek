package com.example.argedik_yemek.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.argedik_yemek.model.Yemek
import com.example.argedik_yemek.servis.YemekDataBase
import kotlinx.coroutines.launch

class Sayfa2YemekDetayiVM(application: Application) : BaseViewModel(application) {

    val yemekLiveData = MutableLiveData<Yemek>()

    fun roomVerisiniAl(uuid : Int){
        launch {
            val dao = YemekDataBase(getApplication()).yemekDao()
            val yemek = dao.getYemek(uuid)
            yemekLiveData.value = yemek
        }
    }
}