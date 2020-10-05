package com.example.photogallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.photogallery.api.FlickrFetch

class PhotoGalleryViewModel : ViewModel() {

    val galleryItemLiveData: LiveData<List<GalleryItem>> = FlickrFetch().fetchPhotos()

}