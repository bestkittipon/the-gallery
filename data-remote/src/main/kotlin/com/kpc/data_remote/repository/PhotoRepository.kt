package com.kpc.data_remote.repository

import com.kpc.data_remote.api.GalleryApiService
import com.kpc.data_remote.meppers.toDomain
import com.kpc.data_remote.models.PhotosResponse
import com.kpc.domain.model.Photos
import com.kpc.domain.repository.IPhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PhotoRepository(
    private val apiService: GalleryApiService
): IPhotoRepository {

    override suspend fun getPhotos(page: String): Flow<Photos> = flow {
        val photosResponse = apiService.getPhotos(page)
        val photos = PhotosResponse(photosResponse)
        emit(photos.toDomain())
    }
}