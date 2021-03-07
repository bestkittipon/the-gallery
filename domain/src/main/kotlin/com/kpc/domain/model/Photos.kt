package com.kpc.domain.model


data class Photos(
    val photos: ArrayList<PhotoItemDomain>
)

data class PhotoItemDomain(
    val albumId: Int?,
    val id: Int?,
    val title: String?,
    val url: String?,
    val thumbnailUrl: String?
)