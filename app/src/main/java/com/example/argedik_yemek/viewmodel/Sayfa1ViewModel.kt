package com.example.argedik_yemek.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.argedik_yemek.model.Yemek
import com.example.argedik_yemek.servis.YemekAPIServis
import com.example.argedik_yemek.servis.YemekDataBase
import com.example.argedik_yemek.util.OzelSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class Sayfa1ViewModel(application: Application) :BaseViewModel(application){
//class Sayfa1ViewModel():ViewModel() {
    val yemekler = MutableLiveData<List<Yemek>>()
    val yemekHataMesajı = MutableLiveData<Boolean>()
    val yemekYukleniyor = MutableLiveData<Boolean>()
    //dakika 10 olarak ayarlandı 10 u değiştirebilirsin
    private var guncellemeZamani = 10 * 60 *1000*1000*1000L


    private val yemekApiServis = YemekAPIServis()
    private val disposable  = CompositeDisposable()
    private val ozelSharedPreferences = OzelSharedPreferences(getApplication())

    fun refreshData(){
        val kaydedilmeZamani = ozelSharedPreferences.zamaniAl()
        if (kaydedilmeZamani != null && kaydedilmeZamani!=0L && System.nanoTime()-kaydedilmeZamani<guncellemeZamani){
            //Sqlite'den çek
            sqliteDenAl()
        }else{
            internettenVeriAl()
        }
        internettenVeriAl()
    }

    fun refreshFromInternet(){
        internettenVeriAl()
    }

    private fun sqliteDenAl(){
        yemekYukleniyor.value=true
        launch {
            val yemekListesi = YemekDataBase(getApplication()).yemekDao().getAllYemek()
            yemekleriGoster(yemekListesi)
            Toast.makeText(getApplication(),"Yemek Listesi internetsiz alındı",Toast.LENGTH_LONG).show()
        }
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
                                sqliteSakla(t)

                                /*yemekler.value = t
                                yemekHataMesajı.value=false
                                yemekYukleniyor.value=false*/
                                Toast.makeText(getApplication(),"Yemek Listesi internetten alındı",Toast.LENGTH_LONG).show()
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

    private fun yemekleriGoster(yemeklerListesi:List<Yemek>){
        yemekler.value = yemeklerListesi
        yemekHataMesajı.value=false
        yemekYukleniyor.value=false
    }

    private fun sqliteSakla(yemekListesi:List<Yemek>){
        launch {
            val dao = YemekDataBase(getApplication()).yemekDao()
            dao.deleteAllYemek()

            //verileri tektek almak için: *yemekListesi.toTypedArray()
            //uygulama içindeki idlere ulaşmak için uuid Listesi
            val uuidListesi = dao.insertAll(*yemekListesi.toTypedArray())
            var i = 0
            while (i<yemekListesi.size){
                //sqlite de oluşturulan uuidleri modelin içine koyuyoruz.
                yemekListesi[i].uuid = uuidListesi[i].toInt()
                i = i+1
            }
            yemekleriGoster(yemekListesi)
        }
        ozelSharedPreferences.zamaniKaydet(System.nanoTime())
    }
}