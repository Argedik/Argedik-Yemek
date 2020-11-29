package com.example.argedik_yemek.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.argedik_yemek.R
import com.example.argedik_yemek.databinding.RecyclerRowBinding
import com.example.argedik_yemek.model.Yemek
import com.example.argedik_yemek.util.gorselIndir
import com.example.argedik_yemek.util.placeHolderYap
import com.example.argedik_yemek.view.YemekListesiFragmentDirections
import kotlinx.android.synthetic.main.recycler_row.view.*

class YemekRecyclerAdapter(val yemekListesi:ArrayList<Yemek>): RecyclerView.Adapter<YemekRecyclerAdapter.YemekVH>(),YemekClickListener{
    //class YemekVH(itemView: View): RecyclerView.ViewHolder(itemView) {
    class YemekVH(var view: RecyclerRowBinding): RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekVH {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.recycler_row,parent,false)
        //return YemekVH(view)
        val view = DataBindingUtil.inflate<RecyclerRowBinding>(inflater,R.layout.recycler_row,parent,false)
        return YemekVH(view)
    }

    override fun onBindViewHolder(holder: YemekVH, position: Int) {

        holder.view.yemek=yemekListesi[position]
        holder.view.listener = this
        /*holder.itemView.recycler_row_email.text=yemekListesi.get(position).aisim
        holder.itemView.recycler_row_yorum.text=yemekListesi.get(position).Tarif
        holder.itemView.recycler_row_resim.gorselIndir(yemekListesi.get(position).gorsel, placeHolderYap(holder.itemView.context))
        //picasso ile resimleri alma
        //Picasso.get().load(yemekListesi[position].gorsel).into(holder.itemView.recycler_row_resim)
        holder.itemView.setOnClickListener{
            val action= YemekListesiFragmentDirections.actionYemekListesiFragmentToYemekDetayiFragment(yemekListesi.get(position).uuid)
            Navigation.findNavController(it).navigate(action)
        }*/
    }

    override fun getItemCount(): Int {
        return yemekListesi.size
    }


    fun YemekListesiGuncelle(yeniYemekListesi: List<Yemek>){
        yemekListesi.clear()
        yemekListesi.addAll(yeniYemekListesi)
        notifyDataSetChanged()
    }

    override fun yemekTiklandi(view: View) {
        val uuid = view.yemek_uuid.text.toString().toIntOrNull()
        uuid?.let {
            val action= YemekListesiFragmentDirections.actionYemekListesiFragmentToYemekDetayiFragment(it)
            Navigation.findNavController(view).navigate(action)
        }

    }
}