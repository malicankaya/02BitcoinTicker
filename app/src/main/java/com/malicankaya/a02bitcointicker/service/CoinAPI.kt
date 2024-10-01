package com.malicankaya.a02bitcointicker.service

import com.malicankaya.a02bitcointicker.model.CoinModel
import retrofit2.http.GET


interface CoinAPI {

    //base url -> https://api.coingecko.com/api/v3/

    @GET("")
    suspend fun getAllCoins() : List<CoinModel>

    @GET("")
    suspend fun getCoinbyId() : CoinModel
}