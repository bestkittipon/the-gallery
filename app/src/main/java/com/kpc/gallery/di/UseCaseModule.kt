package com.kpc.gallery.di

import com.kpc.domain.repository.IPhotoRepository
import com.kpc.domain.usecases.*
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCasesModule = module {

    single(named("get_photo")) { providePhotoUseCase(get()) }

}

fun providePhotoUseCase(photoRepository: IPhotoRepository): PhotoBaseUseCase {
    return PhotoUseCase(photoRepository)
}
