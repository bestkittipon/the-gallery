package com.kpc.data_remote.models

import com.squareup.moshi.Json

data class PhotosResponse(
    val photos: List<PhotoItem>
)

data class PhotoItem(
    @field:Json(name = "albumId") val albumId: Int?,
    @field:Json(name = "id") val id: Int?,
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "url") val url: String?,
    @field:Json(name = "thumbnailUrl") val thumbnailUrl: String?
)