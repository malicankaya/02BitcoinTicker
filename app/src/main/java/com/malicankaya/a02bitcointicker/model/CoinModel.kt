package com.malicankaya.a02bitcointicker.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class CoinModel(
    @SerializedName("id")
    val id : String,
    @SerializedName("symbol")
    val symbol : String,
    @SerializedName("name")
    val name : String,
    @SerializedName("image")
    val image : String,
    @SerializedName("current_price")
    val current_price : Double,
    @SerializedName("high_24h")
    val high_24h : Double,
    @SerializedName("low_24h")
    val low_24h : Double,
    @SerializedName("price_change_24h")
    val price_change_24h : Double,
    @SerializedName("price_change_percentage_24h")
    val price_change_percentage_24h : Double,
    @SerializedName("last_updated")
    val last_updated : Date
)