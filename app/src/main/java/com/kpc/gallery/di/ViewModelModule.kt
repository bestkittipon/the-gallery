package com.kpc.gallery.di

import com.kpc.gallery.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        MainViewModel(
            photoUseCase = get(named("get_photo"))
        )
    }
}
