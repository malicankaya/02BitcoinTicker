package com.malicankaya.a02bitcointicker.model

data class CoinModel(
    val id : Int,
    val code : String,
    val name : String,
    val image : String,
    val price : Int,
    val priceChange : Int,
){}