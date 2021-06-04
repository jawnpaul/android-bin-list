package com.example.binlist.data.remote

import com.example.binlist.data.entities.ResponseResource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BinApiService {

    @GET("/{id}")
    suspend fun getBinNumber(@Path("id") id: Int): Response<ResponseResource>
}