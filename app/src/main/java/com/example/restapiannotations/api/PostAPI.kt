package com.example.restapiannotations.api

import com.example.restapiannotations.model.Post
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT

interface PostAPI {

    @GET("/posts")
    suspend fun getPosts(): Response<List<Post>>

    @POST("/posts")
    suspend fun createPost(): Response<Post>

    @PUT("/posts/1")
    suspend fun updatePost(): Response<Post>

    @PATCH("/posts/1")
    suspend fun patchPost(): Response<Post>

    @DELETE("/posts/1")
    suspend fun deletePost(): Response<Post>
}