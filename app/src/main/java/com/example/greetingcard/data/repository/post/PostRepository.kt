package com.example.greetingcard.data.repository.post

import com.example.greetingcard.data.model.response.Post
import com.example.greetingcard.data.source.api.PostService
import com.example.greetingcard.data.source.api.RetrofitInstance
import retrofit2.Response

class PostRepository {
    // postService 구현체
    private val postService: PostService = RetrofitInstance.create(PostService::class.java)

    // 게시글 조회
    suspend fun getPostList(): Response<List<Post>> {
        return postService.getPostList()
    }
}
