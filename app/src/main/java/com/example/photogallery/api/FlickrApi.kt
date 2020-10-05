package com.example.photogallery.api

import retrofit2.Call
import retrofit2.http.GET

private const val API_KEY = "33c84a55187ad199aab0061e6b722355"

interface FlickrApi {

    @GET("services/rest/?method=flickr.interestingness.getList" +
            "&api_key=$API_KEY" +
            "&format=json" +
            "&nojsoncallback=1" +
            "&extras=url_s")
    fun fetchPhotos(): Call<FlickrResponse>
}
