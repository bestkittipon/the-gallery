package com.kpc.domain.repository

import com.kpc.domain.model.Photos
import kotlinx.coroutines.flow.Flow

interface IPhotoRepository {
    suspend fun getPhotos(page: String): Flow<Photos>
}