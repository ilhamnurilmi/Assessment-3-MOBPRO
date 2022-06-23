package com.ilhamnurilmi.todonoted.data.network

import com.ilhamnurilmi.todonoted.data.model.Tips
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/ilhamnurilmi/Static-api/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface TipsApiService {
    @GET("static-api.json")
    suspend fun getTips(): List<Tips>
}
object TipsApi {
    val service: TipsApiService by lazy {
        retrofit.create(TipsApiService::class.java)
    }
    fun getTipsUrl(nama: String): String {
        return "$BASE_URL$nama.png"
    }
}
enum class ApiStatus { LOADING, SUCCESS, FAILED }