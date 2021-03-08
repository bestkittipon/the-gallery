package com.kpc.data_remote.repository

import com.google.common.truth.Truth
import com.kpc.data_remote.BaseTest
import com.kpc.domain.repository.IPhotoRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class PhotoRepositoryTest : BaseTest() {

    private lateinit var photoRepository: IPhotoRepository

    @Before
    override fun setup() {
        super.setup()
        photoRepository = PhotoRepository(galleryApiService)
    }

    @Test
    fun `given existing list when executed then return list of photo results`() {
        runBlocking {
            val result = photoRepository.getPhotos("1")
            result.collect {
                Truth.assertThat(it).isNotNull()
                Truth.assertThat(it.photos).hasSize(50)
                Truth.assertThat(it.photos[0].title?.isNotEmpty()).isTrue()
                Truth.assertThat(it.photos[0].url?.isNotEmpty()).isTrue()
                Truth.assertThat(it.photos[0].thumbnailUrl?.isNotEmpty()).isTrue()
                Truth.assertThat(it.photos[49].title?.isNotEmpty()).isTrue()
                Truth.assertThat(it.photos[49].url?.isNotEmpty()).isTrue()
                Truth.assertThat(it.photos[49].thumbnailUrl?.isNotEmpty()).isTrue()
            }
        }
    }

    @Test
    fun `given empty list when executed then return empty list of photo results`() {
        runBlocking {
            val result = photoRepository.getPhotos("101")
            result.collect {
                Truth.assertThat(it).isNotNull()
                Truth.assertThat(it.photos).hasSize(0)
            }
        }
    }
}