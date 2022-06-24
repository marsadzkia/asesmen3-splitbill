package org.d3if2107.splitbill.rekomendasi

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if2107.splitbill.MainActivity
import org.d3if2107.splitbill.R
import org.d3if2107.splitbill.internet.RekomendasiApi
import org.d3if2107.splitbill.internet.RekomendasiStatus
import org.d3if2107.splitbill.internet.UpdateWorker
import org.d3if2107.splitbill.model.Rekomendasi
import java.lang.Exception
import java.util.concurrent.TimeUnit

class RekomendasiViewModel : ViewModel() {
    private val data = MutableLiveData<List<Rekomendasi>>()
    private val status = MutableLiveData<RekomendasiStatus>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(RekomendasiStatus.LOADING)
            try {
                data.postValue(RekomendasiApi.service.getRekomendasi())
                status.postValue(RekomendasiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("RekomendasiViewModel", "Failure: ${e.message}")
                status.postValue(RekomendasiStatus.FAILED)
            }
        }
    }
    fun getData(): LiveData<List<Rekomendasi>> = data
    fun getStatus(): LiveData<RekomendasiStatus> = status
    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(10, TimeUnit.SECONDS)
            .build()

        WorkManager.getInstance(app).enqueueUniqueWork(
            MainActivity.CHANNEL_ID, ExistingWorkPolicy.REPLACE, request
        )
    }
}