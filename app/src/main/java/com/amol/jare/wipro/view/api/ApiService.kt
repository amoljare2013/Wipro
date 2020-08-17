package com.ankit.jare.model.api

import com.amol.jare.wiproapp.model.AlbumModel
import com.ankit.jare.utils.Constants.Companion.APIURL
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(APIURL)
    fun getList(
        @Query("method") method: String,
        @Query("album") album: String,
        @Query("api_key") api_key: String,
        @Query("format") format: String
    ): Call<AlbumModel>
}