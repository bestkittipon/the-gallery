package com.kpc.gallery.model

data class PhotosPresentation(
    val photos: List<PhotoItemPresentation>
)

data class PhotoItemPresentation(
    val albumId: Int?,
    val id: Int?,
    val title: String?,
    val url: String?,
    val thumbnailUrl: String?
)