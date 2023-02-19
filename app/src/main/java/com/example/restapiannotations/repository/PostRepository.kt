package com.example.restapiannotations.repository

import com.example.restapiannotations.api.RetrofitInstance

class PostRepository {

    suspend fun getPosts() = RetrofitInstance.api.getPosts()

    suspend fun getComments(postId: Int) = RetrofitInstance.api.getComments(postId)

    suspend fun getCommentsQuery(postId: Int) = RetrofitInstance.api.getCommentsQuery(postId)
}