package com.example.argedik_yemek.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.argedik_yemek.model.Post
import com.example.argedik_yemek.model.Yemek
import com.example.argedik_yemek.servis.YemekAPIServis
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class Sayfa1ViewModel :ViewModel(){
    val yemekler = MutableLiveData<List<Yemek>>()
    val yemekHataMesajı = MutableLiveData<Boolean>()
    val yemekYukleniyor = MutableLiveData<Boolean>()

    private val yemekApiServis = YemekAPIServis()
    private val disposable  = CompositeDisposable()

    fun refreshData(){
        internettenVeriAl()

    }

    private fun internettenVeriAl(){
        yemekYukleniyor.value=true

        //IO input output veri alışverişinde kullandığımız thread
        //Default: cpu yoğun görsel işlemlerde kullanılıyor.
        //UI: kullanıcı arayüzü ile yaptığımız işlemler

        disposable.add(
                yemekApiServis.getData()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<List<Yemek>>(){
                            override fun onSuccess(t: List<Yemek>) {
                                //Başarılı olursa
                                yemekler.value = t
                                yemekHataMesajı.value=false
                                yemekYukleniyor.value=false
                            }

                            override fun onError(e: Throwable) {
                                //Hatalı olursa
                                yemekHataMesajı.value=true
                                yemekYukleniyor.value=false
                                e.printStackTrace()
                            }

                        })
        )
    }
}