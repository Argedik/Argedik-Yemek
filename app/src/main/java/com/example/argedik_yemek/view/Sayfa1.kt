package com.example.argedik_yemek.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.argedik_yemek.R
import com.example.argedik_yemek.adapter.RecyclerAdapter
import com.example.argedik_yemek.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.ana_menu.*

class Sayfa1:AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseFirestore
    private lateinit var recyclerViewAdapter : RecyclerAdapter

    var postListesi=ArrayList<Post>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ana_menu)

        auth= FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()

        verileriAl()

        var layoutManager= LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager
        recyclerViewAdapter = RecyclerAdapter(postListesi)
        recyclerView.adapter=recyclerViewAdapter
    }

    fun verileriAl(){
        database.collection("Post").orderBy("tarih", Query.Direction.DESCENDING).addSnapshotListener { snapshot, exception ->
            if(exception!=null){
                Toast.makeText(this,exception.localizedMessage, Toast.LENGTH_LONG).show()
            }else{
                if(snapshot!=null){
                    //(snapshot.isEmpty==false) = (!snapshot.isEmpty)
                    if (!snapshot.isEmpty){

                        val documents= snapshot.documents
                        postListesi.clear()
                        for (document in documents){
                            val kullaniciEmail= document.get("kullaniciemail") as String
                            val kullaniciYorumu= document.get("kullaniciyorum") as String
                            val gorselUrl=document.get("gorselurl") as String

                            val indirilenPost = Post(kullaniciEmail,kullaniciYorumu,gorselUrl)
                            postListesi.add(indirilenPost)
                        }
                        recyclerViewAdapter.notifyDataSetChanged()
                    }
                }
            }
        }

    }

    //menu oluşturma
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.bottom_nav_menu ,menu)
        return super.onCreateOptionsMenu(menu)
    }
    //bir menü seçilirse
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.hesapdegis){
            auth.signOut()
            val intent= Intent(this, Giris_Sayfasi::class.java)
            startActivity(intent)
            finish()

        }/*else if(item.itemId == R.id.cikis_yap){
            auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }*/
        return super.onOptionsItemSelected(item)
    }
}