package com.malicankaya.a02bitcointicker.service

import android.icu.util.Currency
import com.malicankaya.a02bitcointicker.model.CoinModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CoinAPI {

    //base url -> https://api.coingecko.com/api/v3/

    @GET("coins/markets")
    suspend fun getAllCoins(
        @Query("vs_currency") currency: String = "usd",
    ) : List<CoinModel>

    @GET("coins/{id}")
    suspend fun getCoinbyId(
        @Path("id") id : String,
        ) : CoinModel
}