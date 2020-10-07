package com.example.photogallery.api

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.photogallery.GalleryItem
import kotlinx.coroutines.flow.Flow

private const val TAG = "FlickrRepository"

class FlickrRepository(private val service: FlickrService) {
    
    fun getSearchResultStream(): Flow<PagingData<GalleryItem>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { FlickrPagingSource(service) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 100
    }
}