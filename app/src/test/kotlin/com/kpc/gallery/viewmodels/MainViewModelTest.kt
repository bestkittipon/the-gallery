package com.kpc.gallery.viewmodels

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.kpc.gallery.BaseViewModelTest
import com.kpc.gallery.R
import com.kpc.gallery.adapter.CurrencyAdapter
import com.kpc.gallery.fakes.FakePhotosUseCase
import com.kpc.gallery.utils.UiState
import com.kpc.gallery.utils.observeOnce
import com.kpc.gallery.viewmodel.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
internal class MainViewModelTest: BaseViewModelTest() {

    private lateinit var mainViewModel: MainViewModel

    @Test
    fun given_a_photo_list_then_get_result() {
        coroutineTestRule.dispatcher.runBlockingTest {
            prepareViewModel(UiState.SUCCESS)

            mainViewModel.getPhoto()

            mainViewModel.photoResult.observeOnce { photo ->
                Truth.assertThat(photo.photos.isNotEmpty()).isTrue()
                Truth.assertThat(photo.photos[0].thumbnailUrl?.isNotEmpty()).isTrue()
                Truth.assertThat(photo.photos[0].title?.isNotEmpty()).isTrue()
                Truth.assertThat(photo.photos[0].url?.isNotEmpty()).isTrue()
                Truth.assertThat(mainViewModel.adapter.itemCount > 0).isTrue()
                Truth.assertThat(mainViewModel.adapter.getData()[0].thumbnailUrl?.isNotEmpty()).isTrue()
                Truth.assertThat(mainViewModel.adapter.getData()[0].title?.isNotEmpty()).isTrue()
                Truth.assertThat(mainViewModel.adapter.getData()[0].url?.isNotEmpty()).isTrue()
            }
        }
    }

    @Test
    fun given_a_photo_list_more_then_get_result() {
        coroutineTestRule.dispatcher.runBlockingTest {
            prepareViewModel(UiState.SUCCESS)
            mainViewModel.loadMorePhoto()

            mainViewModel.photoResult.observeOnce { photo ->
                Truth.assertThat(photo.photos.isNotEmpty()).isTrue()
                Truth.assertThat(photo.photos[0].thumbnailUrl?.isNotEmpty()).isTrue()
                Truth.assertThat(photo.photos[0].title?.isNotEmpty()).isTrue()
                Truth.assertThat(photo.photos[0].url?.isNotEmpty()).isTrue()
                Truth.assertThat(mainViewModel.adapter.itemCount > 0).isTrue()
                Truth.assertThat(mainViewModel.adapter.getData()[0].thumbnailUrl?.isNotEmpty()).isTrue()
                Truth.assertThat(mainViewModel.adapter.getData()[0].title?.isNotEmpty()).isTrue()
                Truth.assertThat(mainViewModel.adapter.getData()[0].url?.isNotEmpty()).isTrue()
                Truth.assertThat(mainViewModel.adapter.isPositionFooter(0)).isTrue()
            }
        }
    }

    @Test
    fun given_an_invalid_state_then_get_view_state_error() {
        coroutineTestRule.dispatcher.runBlockingTest {
            prepareViewModel(UiState.ERROR)

            mainViewModel.getPhoto()

            mainViewModel.error.observeOnce { error ->
                Truth.assertThat(error).isNotEmpty()
            }
        }
    }
    
    override fun prepareViewModel(uiState: UiState) {
        val getPhotoListUseCase = FakePhotosUseCase(uiState)
        mainViewModel = MainViewModel(getPhotoListUseCase)
    }
}