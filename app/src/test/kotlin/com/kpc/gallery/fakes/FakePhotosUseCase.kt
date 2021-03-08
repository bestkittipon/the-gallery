package com.kpc.gallery.fakes

import com.kpc.domain.model.Photos
import com.kpc.domain.usecases.PhotoBaseUseCase
import com.kpc.gallery.utils.Data
import com.kpc.gallery.utils.UiState


class FakePhotosUseCase(
    uiState: UiState
): BaseTestUseCase<Photos, String>(uiState), PhotoBaseUseCase {
    override fun getValue(params: String): Photos {
        return Data.photo
    }

    override suspend fun invoke(params: String) = execute(params)

}