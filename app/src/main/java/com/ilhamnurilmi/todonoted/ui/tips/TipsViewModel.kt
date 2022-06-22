package com.ilhamnurilmi.todonoted.ui.tips

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhamnurilmi.todonoted.data.model.Tips
import com.ilhamnurilmi.todonoted.data.network.ApiStatus
import com.ilhamnurilmi.todonoted.data.network.TipsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TipsViewModel : ViewModel() {
    private val tipsData = MutableLiveData<List<Tips>>()
    private val status = MutableLiveData<ApiStatus>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                tipsData.postValue(TipsApi.service.getTips())
                status.postValue(ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("TipsViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    fun getTipsData(): LiveData<List<Tips>> = tipsData
    fun getStatus(): LiveData<ApiStatus> = status
}