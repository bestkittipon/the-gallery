package com.kpc.domain.usecases

import com.kpc.domain.model.Photos
import com.kpc.domain.repository.IPhotoRepository
import kotlinx.coroutines.flow.Flow

typealias PhotoBaseUseCase = BaseUseCase<String, Flow<Photos>>

class PhotoUseCase(
    private val photoRepository: IPhotoRepository
) : PhotoBaseUseCase {

    override suspend operator fun invoke(params: String) = photoRepository.getPhotos(params)

}