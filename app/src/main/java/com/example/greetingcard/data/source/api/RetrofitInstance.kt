package com.example.greetingcard.data.source.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// 싱글톤으로 레트로핏 인스턴스 관리
object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:8080"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }
}
