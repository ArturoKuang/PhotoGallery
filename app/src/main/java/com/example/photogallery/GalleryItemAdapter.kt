package com.example.photogallery

import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class GalleryItemAdapter : PagingDataAdapter<GalleryItem, GalleryItemHolder>(GALLERYITEM_COMPARATOR) {
    override fun onBindViewHolder(holder: GalleryItemHolder, position: Int) {
        val galleryItem = getItem(position)
        if (galleryItem != null) {
            holder.bindTitle(galleryItem.title)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryItemHolder {
        val textView = TextView(parent.context)
        return GalleryItemHolder(textView)
    }

    companion object {
        private val GALLERYITEM_COMPARATOR = object : DiffUtil.ItemCallback<GalleryItem>() {
            override fun areItemsTheSame(oldItem: GalleryItem, newItem: GalleryItem) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: GalleryItem, newItem: GalleryItem) =
                oldItem == newItem
        }
    }
}

class GalleryItemHolder(itemTextView: TextView) :
    RecyclerView.ViewHolder(itemTextView) {

    val bindTitle: (CharSequence) -> Unit = itemTextView::setText
}