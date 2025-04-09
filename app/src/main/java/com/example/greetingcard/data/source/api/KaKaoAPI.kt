package com.example.greetingcard.data.source.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KaKaoAPI {

    @GET("v2/search/image")
    fun getSearchImage(
        @Header("Authorization") token: String,
        @Query("query") query: String
    ): Call<Map<String, Any>>
}
