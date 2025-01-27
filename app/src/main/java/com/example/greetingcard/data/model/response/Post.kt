package com.example.greetingcard.data.model.response

data class Post(
    val id: Int,
    val title: String,
    val detail: String,
    val planId: Int?,
    val userId: Int,
    val createTime: String,
    val modifyTime: String,
    val userProfileImgUrl: String?,
)
