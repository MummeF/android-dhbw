package com.dhbw.mummefelix.uebung04

data class JSONData (
    var user: String,
    var lastLogin: String,
    var movies: ArrayList<Movie>
)