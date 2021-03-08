package com.kpc.gallery

import com.kpc.gallery.utils.CoroutineTestRule
import com.kpc.gallery.utils.UiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.koin.test.AutoCloseKoinTest


abstract class BaseViewModelTest : AutoCloseKoinTest() {
    @ExperimentalCoroutinesApi
    @get:Rule
    open val coroutineTestRule = CoroutineTestRule()

    abstract fun prepareViewModel(uiState: UiState)
}