package com.example.photogallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.photogallery.api.FlickrFetch
import com.example.photogallery.api.FlickrRepository
import kotlinx.coroutines.flow.Flow

class PhotoGalleryViewModel(private val repository: FlickrRepository) : ViewModel() {

    //val galleryItemLiveData: LiveData<List<GalleryItem>> = FlickrFetch().fetchPhotos()

    private var currentSearchResult: Flow<PagingData<GalleryItem>>? = null

    fun searchRepo(): Flow<PagingData<GalleryItem>> {
        val lastResult = currentSearchResult
        if (lastResult != null) {
            return lastResult
        }

        val newResult: Flow<PagingData<GalleryItem>> = repository.getSearchResultStream()
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}