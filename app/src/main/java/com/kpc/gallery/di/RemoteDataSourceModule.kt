package com.kpc.gallery.di

import com.kpc.data_remote.repository.PhotoRepository
import com.kpc.domain.repository.IPhotoRepository
import org.koin.dsl.module


val remoteDataSourceModule = module {

    single<IPhotoRepository> { PhotoRepository(apiService = get()) }

}
