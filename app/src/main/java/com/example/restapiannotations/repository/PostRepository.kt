package com.example.restapiannotations.repository

import com.example.restapiannotations.api.RetrofitInstance

class PostRepository {

    suspend fun getPosts() = RetrofitInstance.api.getPosts()

    suspend fun getComments(postId: Int) = RetrofitInstance.api.getComments(postId)
}