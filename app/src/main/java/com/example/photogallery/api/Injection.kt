package com.example.photogallery.api

import androidx.lifecycle.ViewModelProvider

object Injection {

    private fun provideGithubRepository(): FlickrRepository {
        return FlickrRepository(FlickrService.create())
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(provideGithubRepository())
    }
}
