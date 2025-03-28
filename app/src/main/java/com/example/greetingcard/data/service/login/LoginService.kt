package com.example.greetingcard.data.service.login

import com.example.greetingcard.data.model.dto.user.UserDTO
import com.example.greetingcard.data.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<LoginResponse>

    @POST("register")
    suspend fun register(
        @Body userDTO: UserDTO
    )


    @POST("api/user/test")
    suspend fun loginTest(
        @Body userDTO: UserDTO,
    ): Response<UserDTO>
}