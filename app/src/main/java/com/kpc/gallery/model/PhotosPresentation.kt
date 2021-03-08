package com.kpc.gallery.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class PhotosPresentation(
    val photos: List<PhotoItemPresentation>
)

@Parcelize
data class PhotoItemPresentation(
    val albumId: Int?,
    val id: Int?,
    val title: String?,
    val url: String?,
    val thumbnailUrl: String?
): Parcelable