package com.example.restapiannotations.repository

import com.example.restapiannotations.api.RetrofitInstance
import com.example.restapiannotations.model.Post

class PostRepository {

    suspend fun getPosts() = RetrofitInstance.api.getPosts()

    suspend fun getComments(postId: Int) = RetrofitInstance.api.getComments(postId)

    suspend fun getCommentsQuery(postId: Int) = RetrofitInstance.api.getCommentsQuery(postId)

    suspend fun getCommentsQueryMap(parameters: Map<String, String>) = RetrofitInstance.api.getCommentsQueryMap(parameters)

    suspend fun getCommentsOnUrl(url: String) = RetrofitInstance.api.getCommentsOnQuery(url)

    suspend fun createPost(post: Post) = RetrofitInstance.api.createPost(post)

    suspend fun createPostField(
        userId: Int,
        title: String,
        body: String
    ) = RetrofitInstance.api.createPostField(userId, title, body)

    suspend fun createPostFieldMap(fields: Map<String, String>) = RetrofitInstance.api.createPostFieldMap(fields)
}