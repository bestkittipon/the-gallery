package com.kpc.data_remote.meppers

import com.kpc.data_remote.models.PhotoItem
import com.kpc.data_remote.models.PhotosResponse
import com.kpc.domain.model.PhotoItemDomain
import com.kpc.domain.model.Photos

internal fun PhotosResponse.toDomain(): Photos {
    return Photos(this.photos.map { it.toDomain() } as ArrayList<PhotoItemDomain>)
}

internal fun PhotoItem.toDomain(): PhotoItemDomain {
    return PhotoItemDomain(this.albumId, this.id, this.title, this.url, this.thumbnailUrl)
}
