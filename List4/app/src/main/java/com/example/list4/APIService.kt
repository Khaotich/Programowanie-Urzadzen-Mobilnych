package com.example.list4

import com.example.list4.ui.cities.Post
import com.example.list4.ui.flags.PostImg
import retrofit2.Call
import retrofit2.http.GET


interface PlaceholderApi {
    @GET("/v2/all?field=name,capital")
    fun posts(): Call<List<Post>>

    @GET("/v2/all?field=name,flag")
    fun posts_img(): Call<List<PostImg>>
}
