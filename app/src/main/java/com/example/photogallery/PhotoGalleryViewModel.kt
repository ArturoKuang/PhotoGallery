package com.example.photogallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.photogallery.api.FlickrFetch
import kotlinx.coroutines.flow.Flow

class PhotoGalleryViewModel : ViewModel() {

    val galleryItemLiveData: LiveData<List<GalleryItem>> = FlickrFetch().fetchPhotos()

    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<GalleryItem>>? = null

    fun searchRepo(queryString: String): Flow<PagingData<GalleryItem>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<GalleryItem>> = repository.getSearchResultStream(queryString)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}