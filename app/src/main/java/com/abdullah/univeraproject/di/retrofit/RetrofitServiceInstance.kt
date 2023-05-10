package com.abdullah.univeraproject.di.retrofit

import com.abdullah.univeraproject.models.Album
import com.abdullah.univeraproject.models.Comment
import com.abdullah.univeraproject.models.Photo
import com.abdullah.univeraproject.models.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServiceInstance {
    @GET("posts")
    fun getPost(): Call<List<Post>>

    @GET("albums")
    fun getAlbum(): Call<List<Album>>

    @GET("photos")
    fun getPhoto(@Query("albumId") id: Int): Call<List<Photo>>

    @GET("comments")
    fun getComment(
        @Query("photoId") id: Int,
        @Query("_start") start: Int,
        @Query("_limit") limit: Int,
    ): Call<List<Comment>>
}