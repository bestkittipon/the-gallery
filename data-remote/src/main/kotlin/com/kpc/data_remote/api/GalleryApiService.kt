package com.kpc.data_remote.api

import com.kpc.data_remote.models.*
import retrofit2.http.GET
import retrofit2.http.Path

interface GalleryApiService {
    @GET("albums/{page}/photos")
    suspend fun getPhotos(@Path("page") page: String): List<PhotoItem>
}