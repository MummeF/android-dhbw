package com.dhbw.mummefelix.androiduebung

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Karre(
    var bezeichnung: String,
    var ps: Int,
    var farbe: String
) : Parcelable