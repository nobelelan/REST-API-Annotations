package com.example.restapiannotations.api

import com.example.restapiannotations.model.Comment
import com.example.restapiannotations.model.Post
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
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

    @FormUrlEncoded
    @POST("/posts")
    suspend fun createPostField(
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") body: String
    ): Response<Post>

    @FormUrlEncoded
    @POST("/posts")
    suspend fun createPostFieldMap(@FieldMap fields: Map<String, String>): Response<Post>

    @PUT("/posts/{id}")
    suspend fun updatePost(@Path("id") id: Int, @Body post: Post): Response<Post>

    @PATCH("/posts/{id}")
    suspend fun patchPost(@Path("id") id: Int, @Body post: Post): Response<Post>

    @DELETE("/posts/{id}")
    suspend fun deletePost(@Path("id") id: Int): Response<Unit>
}

// NOTES FOR MYSELF
// 1. I can directly put the full url of an API, this will override the base url, Applicable for @Url too
// 2. I can put as many Query Parameters as I want, Path Parameters are also similar
// 3. When we send request with full Url, we should end the url with '/'
// 4. For other formats like string/xml, we need to use other converters instead of Gson
// 5. FormUrlEncoded is used to form a url like putting the &/space in the correct place,
//      generally used in HTML forms, suitable for key-value pairs
// 6. @PUT will replace the whole object while @PATCH only replaces the fields we pass over