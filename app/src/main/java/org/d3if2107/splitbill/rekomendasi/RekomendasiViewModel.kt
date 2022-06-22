package org.d3if2107.splitbill.rekomendasi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if2107.splitbill.R
import org.d3if2107.splitbill.model.Rekomendasi

class RekomendasiViewModel : ViewModel() {
    private val data = MutableLiveData<List<Rekomendasi>>()

    init {
        data.value = initData()
    }

    private fun initData(): List<Rekomendasi> {
        return listOf(
            Rekomendasi(
                R.drawable.sushitei_logo, "Sushi Tei memiliki rata-rata rating 4 dari 5 " +
                    "pada trip advisor. Siapa yang suka kesegaran ikan pada sushi wajib coba."),
            Rekomendasi(
                R.drawable.solaria_logo, "Solaria memiliki rata-rata rating 3.5 dari 5 " +
                    "pada trip advisor. Menu favoritnya adalah nasi ayam teriaki dan nasi goreng bebek cabe ijo."),
            Rekomendasi(
                R.drawable.bakmigm_logo, "Bakmi GM memiliki rata-rata rating 4 dari 5 " +
                    "pada trip advisor. Rekomendasi menunya pangsit goreng dan bakmi GM spesial."),
            Rekomendasi(
                R.drawable.esteler_logo, "Es Teler 77 memiliki rata-rata rating 3 dari 5 " +
                    "pada trip advisor. Yang menyajikan kesegaran makanan dan minuman khas Indonesia sejak 1982."),
            Rekomendasi(
                R.drawable.logo_hokben, "Hokben memiliki rata-rata rating 3.5 dari 5" +
                    " pada trip advisor. Restoran siap saji khas Jepang yang dapat dinikmati semua kalangan masyarakat.")
        )
    }
    fun getData(): LiveData<List<Rekomendasi>> = data
}