package com.ilhamnurilmi.todonoted.ui.tips

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.ilhamnurilmi.todonoted.data.model.Tips
import com.ilhamnurilmi.todonoted.data.network.ApiStatus
import com.ilhamnurilmi.todonoted.data.network.TipsApi
import com.ilhamnurilmi.todonoted.data.network.UpdateWorker
import com.ilhamnurilmi.todonoted.ui.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

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

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(app).enqueueUniqueWork(
            MainActivity.CHANNEL_ID,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }

}