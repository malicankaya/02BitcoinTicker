package com.malicankaya.a02bitcointicker.service

import com.malicankaya.a02bitcointicker.BuildConfig
import com.malicankaya.a02bitcointicker.model.CoinModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoinAPIService {

    private val interceptor = Interceptor { chain ->
        val request : Request = chain.request()
            .newBuilder()
            .header("accept","application/json")
            .header("x-cg-demo-api-key",BuildConfig.API_KEY)
            .build()
        chain.proceed(request)
    }

    private val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val retrofitAPI = Retrofit.Builder()
        .baseUrl("https://api.coingecko.com/api/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient().newBuilder().addInterceptor(interceptor).addInterceptor(logging).build())
        .build()
        .create(CoinAPI::class.java)

    suspend fun getAllCoins() : List<CoinModel> {
        return retrofitAPI.getAllCoins()
    }

    suspend fun getCoinbyId(id : String) : CoinModel{
        return retrofitAPI.getCoinbyId(id)
    }

}