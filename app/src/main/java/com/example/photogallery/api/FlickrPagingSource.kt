package com.example.photogallery.api

import androidx.paging.PagingSource
import com.example.photogallery.GalleryItem
import retrofit2.HttpException
import java.io.IOException

private const val FLICKR_STARTING_PAGE_INDEX = 1

class FlickrPagingSource(private val service: FlickrService): PagingSource<Int, GalleryItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryItem> {
        val position = params.key ?: FLICKR_STARTING_PAGE_INDEX
        return try {
            val response = service.fetchPhotos(position, params.loadSize)
            val photosResponse = response.photos
            val photos = photosResponse.galleryItems

            LoadResult.Page(
                data = photos,
                prevKey = if (position == FLICKR_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}