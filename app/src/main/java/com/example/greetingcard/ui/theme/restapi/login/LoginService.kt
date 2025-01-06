package com.example.greetingcard.ui.theme.restapi.login

import com.example.greetingcard.data.dto.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginService {

    @FormUrlEncoded
    @POST("/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<LoginResponse>



    @POST("/api/user/test")
    suspend fun loginTest(
        @Body userDTO: UserDTO,
    ): Response<UserDTO>
}