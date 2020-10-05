package com.example.photogallery.api

import android.util.Log
import com.example.photogallery.GalleryItem
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

private const val TAG = "PhotoDeserializer"

class PhotoDeserializer : JsonDeserializer<PhotoResponse> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PhotoResponse {
        val galleryObject = json as JsonObject
        val photosObject = galleryObject.getAsJsonObject("photos")
        val photos = photosObject.getAsJsonArray("photo")

        var galleryItems = mutableListOf<GalleryItem>()
        for(photo in photos) {
            photo as JsonObject
            val title: String = photo.get("title").asString
            val id: String = photo.get("id").asString
            val url: String = photo.get("url_s").asString
            val galleryItem = GalleryItem(title, id, url)
            galleryItems.add(galleryItem)
        }
        val photoResponse = PhotoResponse()
        photoResponse.galleryItems = galleryItems
        return photoResponse
    }
}