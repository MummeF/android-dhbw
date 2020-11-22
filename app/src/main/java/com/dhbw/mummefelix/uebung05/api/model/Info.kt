package com.dhbw.mummefelix.uebung05.api.model

data class Info(
    val page: Int,
    val results: Int,
    val seed: String,
    val version: String
)