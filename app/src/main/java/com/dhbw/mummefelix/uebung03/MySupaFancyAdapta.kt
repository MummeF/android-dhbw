package com.dhbw.mummefelix.uebung03

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.my_supa_fancy_item.view.*

class MySupaFancyAdapta(private val dataSet: ArrayList<MySupaFancyRaumSchiff>) :
    RecyclerView.Adapter<MySupaFancyAdapta.MySupaFancyViewHolda>() {

    class MySupaFancyViewHolda (val item: CardView) : RecyclerView.ViewHolder (item){
        fun notRlyNeccessaryFunction(stuff: MySupaFancyRaumSchiff) {
            item.supaFancyRaumSchiffImageView.setImageResource(stuff.bild)
            item.supaFancyLayout.setBackgroundColor(stuff.farbe)
            item.mySupaFancyName.text = stuff.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySupaFancyViewHolda {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_supa_fancy_item, parent, false) as CardView
        return MySupaFancyViewHolda(item)

    }

    override fun onBindViewHolder(holder: MySupaFancyViewHolda, position: Int) {
        holder.notRlyNeccessaryFunction(dataSet[position])
    }

    override fun getItemCount() = dataSet.size


}