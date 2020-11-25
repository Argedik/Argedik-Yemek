package com.example.argedik_yemek.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.argedik_yemek.R
import com.example.argedik_yemek.model.Post
import com.example.argedik_yemek.model.Yemek
import com.example.argedik_yemek.view.YemekListesiFragmentDirections
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_row.view.*

class RecyclerAdapter(val postList:ArrayList<Post>) : RecyclerView.Adapter<RecyclerAdapter.PostHolder>() {
    class PostHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        /*val textView: TextView
        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.textView)
        }*/
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_row,parent,false)
        return PostHolder(view)
    }
    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.itemView.recycler_row_email.text = postList[position].kullaniciEmail
        holder.itemView.recycler_row_yorum.text = postList[position].kullaniciYorum
        Picasso.get().load(postList[position].gorselUrl).into(holder.itemView.recycler_row_resim)
        //PostHolder.textView.text = dataSet[position]
    }
    override fun getItemCount(): Int {
        return postList.size
    }


}

class YemekRecyclerAdapter(val yemekListesi:ArrayList<Yemek>):RecyclerView.Adapter<YemekRecyclerAdapter.YemekVH>(){
    class YemekVH(itemView:View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekVH {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_row,parent,false)
        return YemekVH(view)
    }

    override fun onBindViewHolder(holder: YemekVH, position: Int) {
        println(position)
        println(yemekListesi.get(position).Tarif)
        println(yemekListesi.get(position).aisim)
        holder.itemView.recycler_row_email.text=yemekListesi.get(position).aisim
        holder.itemView.recycler_row_yorum.text=yemekListesi.get(position).Tarif
        Picasso.get().load(yemekListesi[position].gorsel).into(holder.itemView.recycler_row_resim)
        holder.itemView.setOnClickListener{
            val action=YemekListesiFragmentDirections.actionYemekListesiFragmentToYemekDetayiFragment(0)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return yemekListesi.size
    }


    fun YemekListesiGuncelle(yeniYemekListesi: List<Yemek>){
        yemekListesi.clear()
        yemekListesi.addAll(yeniYemekListesi)
        notifyDataSetChanged()
    }
}