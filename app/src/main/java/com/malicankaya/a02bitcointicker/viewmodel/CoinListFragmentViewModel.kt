package com.malicankaya.a02bitcointicker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.malicankaya.a02bitcointicker.model.CoinModel
import com.malicankaya.a02bitcointicker.service.CoinAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoinListFragmentViewModel(application: Application) : AndroidViewModel(application) {

    var coinList = MutableLiveData<List<CoinModel>>()
    var isLoading = MutableLiveData<Boolean>()

    fun refreshCoinList(){
        isLoading.value = true
        viewModelScope.launch {
            coinList.value = CoinAPIService().getAllCoins()
            withContext(Dispatchers.Main){
                isLoading.value = false
            }
        }
    }
}