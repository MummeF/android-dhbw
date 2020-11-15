package com.dhbw.mummefelix.uebung04

import com.google.gson.annotations.SerializedName

data class Movie(
    var title: String,
    var year: Int,
    @SerializedName("duration") var durationInMinutes: Int,
    var imdbScore: Float,
    var synchronized: Boolean,
    var cast: ArrayList<String>
)