package com.example.photogallery

import android.app.Application
import androidx.lifecycle.*
import com.example.photogallery.api.FlickrFetcher

class PhotoGalleryViewModel(private val app: Application) : AndroidViewModel(app) {

    val galleryItemLiveData: LiveData<List<GalleryItem>>

    private val flickrFetcher = FlickrFetcher()
    private val mutableSearchTerm = MutableLiveData<String>()

    init {
        mutableSearchTerm.value = QueryPreferences.getStoredQuery(app)

        galleryItemLiveData =
            Transformations.switchMap(mutableSearchTerm) { searchTerm ->
                if(searchTerm.isBlank()) {
                    flickrFetcher.fetchPhotos()
                } else {
                    flickrFetcher.searchPhotos(searchTerm)
                }
            }
    }

    fun fetchPhotos(query: String = "") {
        QueryPreferences.setStoredQuery(app, query)
        mutableSearchTerm.value = query
    }
}