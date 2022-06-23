package org.d3if2107.splitbill.rekomendasi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if2107.splitbill.R
import org.d3if2107.splitbill.internet.RekomendasiApi
import org.d3if2107.splitbill.model.Rekomendasi
import java.lang.Exception

class RekomendasiViewModel : ViewModel() {
    private val data = MutableLiveData<List<Rekomendasi>>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            try {
                data.postValue(RekomendasiApi.service.getRekomendasi())
            } catch (e: Exception) {
                Log.d("RekomendasiViewModel", "Failure: ${e.message}")
            }
        }
    }
    fun getData(): LiveData<List<Rekomendasi>> = data
}