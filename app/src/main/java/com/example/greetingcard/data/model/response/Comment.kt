package com.example.greetingcard.data.model.response

data class Comment(
    val postId: Int, // 게시물 ID
    val userProfileImgUrl: String?, // 유저 프로필 이미지 URL
    val userNickName: String, // 유저 닉네임
    val comment: String, // 댓글 내용
    val commentDate: String, // 댓글 작성일
)
