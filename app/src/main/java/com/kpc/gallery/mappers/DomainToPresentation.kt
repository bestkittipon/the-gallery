package com.kpc.gallery.mappers

import com.kpc.domain.model.*
import com.kpc.gallery.model.*

internal fun Photos.toPresentation(): PhotosPresentation {
    return PhotosPresentation(this.photos.map { it.toPresentation() })
}

internal fun PhotoItemDomain.toPresentation(): PhotoItemPresentation {
    return PhotoItemPresentation(this.albumId, this.id, this.title, this.url, this.thumbnailUrl)
}
