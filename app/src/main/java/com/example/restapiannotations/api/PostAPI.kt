package com.example.restapiannotations.api

import com.example.restapiannotations.model.Comment
import com.example.restapiannotations.model.Post
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface PostAPI {

    @GET("/posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("/posts/{id}/comments")
    suspend fun getComments(@Path("id") postId: Int): Response<List<Comment>>

    @GET("/comments")
    suspend fun getCommentsQuery(@Query("postId") postId: Int): Response<List<Comment>>

    @GET("/posts")
    suspend fun getCommentsQueryMap(@QueryMap parameters: Map<String, String>): Response<List<Post>>

    @GET
    suspend fun getCommentsOnQuery(@Url url: String): Response<List<Comment>>

    @POST("/posts")
    suspend fun createPost(@Body post: Post): Response<Post>

    @PUT("/posts/1")
    suspend fun updatePost(): Response<Post>

    @PATCH("/posts/1")
    suspend fun patchPost(): Response<Post>

    @DELETE("/posts/1")
    suspend fun deletePost(): Response<Post>
}

// NOTES FOR MYSELF
// 1. I can directly put the full url of an API, this will override the base url, Applicable for Url too
// 2. I can put as many Query Parameters as I want, Path Parameters are also similar
// 3. When we send request will full Url, we should end the url with '/'
// 4. For other formats like string/xml, we need to use other converters instead of Gson