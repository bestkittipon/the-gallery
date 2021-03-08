package com.kpc.gallery.utils

import com.kpc.domain.model.PhotoItemDomain
import com.kpc.domain.model.Photos


object Data {
    val photo = Photos(
        photos = arrayListOf(PhotoItemDomain(1, 1, "title", "https://via.placeholder.com/600/92c952", "https://via.placeholder.com/150/92c952"))
    )
}