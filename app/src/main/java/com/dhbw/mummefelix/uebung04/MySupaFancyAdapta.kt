package com.dhbw.mummefelix.uebung04

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movie.view.*

class MySupaFancyAdapta(private val movies: ArrayList<Movie>) :
    RecyclerView.Adapter<MySupaFancyAdapta.MySupaFancyViewHolda>() {

    class MySupaFancyViewHolda(val item: ConstraintLayout) : RecyclerView.ViewHolder(item) {
        fun notRlyNeccessaryFunction(stuff: Movie) {
            item.itemMovieTitle.text = stuff.title
            item.itemMovieRating.text = stuff.imdbScore.toString()
            item.itemMovieYearDuration.text =
                stuff.year.toString() + ", " + stuff.durationInMinutes.toString()
            item.itemMovieCast.text = TextUtils.join(", ", stuff.cast)
            if (stuff.synchronized) {
                item.itemMovieSynchronized.setImageResource(R.drawable.microphone)
            } else {
                item.itemMovieSynchronized.setImageResource(R.drawable.mute_microphone)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySupaFancyViewHolda {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false) as ConstraintLayout
        return MySupaFancyViewHolda(item)

    }

    override fun onBindViewHolder(holder: MySupaFancyViewHolda, position: Int) {
        holder.notRlyNeccessaryFunction(movies[position])
    }

    override fun getItemCount() = movies.size


}