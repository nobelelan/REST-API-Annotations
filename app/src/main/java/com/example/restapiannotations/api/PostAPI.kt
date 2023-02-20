package com.example.restapiannotations.api

import com.example.restapiannotations.model.Comment
import com.example.restapiannotations.model.Post
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface PostAPI {

    @GET("/posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("/posts/{id}/comments")
    suspend fun getComments(@Path("id") postId: Int): Response<List<Comment>>

    @GET("/comments")
    suspend fun getCommentsQuery(@Query("postId") postId: Int): Response<List<Comment>>

    @GET("/posts")
    suspend fun getCommentsQueryMap(@QueryMap parameters: Map<String, String>): Response<List<Post>>

    @POST("/posts")
    suspend fun createPost(): Response<Post>

    @PUT("/posts/1")
    suspend fun updatePost(): Response<Post>

    @PATCH("/posts/1")
    suspend fun patchPost(): Response<Post>

    @DELETE("/posts/1")
    suspend fun deletePost(): Response<Post>
}