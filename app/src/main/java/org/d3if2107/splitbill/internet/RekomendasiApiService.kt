package org.d3if2107.splitbill.internet

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if2107.splitbill.model.Rekomendasi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://raw.githubusercontent.com/marsadzkia/data_repo/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface RekomendasiApiService {
    @GET("data-rekomendasi.json")
    suspend fun getRekomendasi(): List<Rekomendasi>
}

object RekomendasiApi {
    val service: RekomendasiApiService by lazy {
        retrofit.create(RekomendasiApiService::class.java)
    }

    fun getRekomendasiUrl(nama: String): String{
        return "$BASE_URL$nama.png"
    }
}