package com.kpc.gallery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kpc.gallery.R
import com.kpc.gallery.adapter.CurrencyAdapter
import com.kpc.domain.model.PhotoItemDomain
import com.kpc.domain.model.Photos
import com.kpc.domain.usecases.PhotoBaseUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect

class MainViewModel(
    private val photoUseCase: PhotoBaseUseCase
) : BaseViewModel() {

    private var searchJob: Job? = null
    val adapter = CurrencyAdapter(R.layout.item_content, this)

    val photoResult: LiveData<Photos>
        get() = _photoResult
    private val _photoResult = MutableLiveData<Photos>()

    val loadMore: LiveData<Boolean>
        get() = _loadMore
    private val _loadMore = MutableLiveData<Boolean>().apply {
        value = true
    }

    private var page = 1

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        onResultError(exception.message)
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }

    fun getPhoto() {
        searchJob = launchCoroutine {
            onResultLoading()
            photoUseCase(page.toString()).collect { results ->
                onResultComplete(results)
            }
        }
    }

    fun loadMorePhoto() {
        _loadMore.value = false
        searchJob = launchCoroutine {
            page++
            photoUseCase(page.toString()).collect { results ->
                onLoadMoreComplete(results)
            }
        }
    }

    private fun onResultComplete(photos: Photos) {
        _photoResult.postValue(photos)
        _photoResult.value = photos
        adapter.setData(photos.photos)
        adapter.notifyDataSetChanged()
        dismissLoading()
    }

    private fun onLoadMoreComplete(photos: Photos) {
        _loadMore.value = true
        _photoResult.value?.let { photoResult ->
            if (photos.photos.size > 0) {
                photoResult.photos.addAll(photos.photos)
                adapter.setData(photoResult.photos)
                adapter.notifyDataSetChanged()
            } else if (adapter.isPositionFooter(photoResult.photos.size)) {
                adapter.notifyItemRemoved(photoResult.photos.size.plus(1))
            }
        }
    }

    private fun onResultLoading() {
        showLoading()
    }

    private fun onResultError(message: String?) {
        error.postValue(message)
        dismissLoading()
    }
}



