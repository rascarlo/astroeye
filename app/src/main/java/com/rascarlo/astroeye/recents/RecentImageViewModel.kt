package com.rascarlo.astroeye.recents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rascarlo.astroeye.network.AstroEyeApi
import com.rascarlo.astroeye.network.RecentImage
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * view model for the [RecentImagesFragment]
 * use coroutine for [fetchRecentImages] to process result from [AstroEyeApi.retrofitService]
 */
class RecentImageViewModel : ViewModel() {

    /**
     * recent images response
     * use [_recentImages] for internal use
     * use [recentImages] to expose the live data
     */
    private val _recentImages = MutableLiveData<List<RecentImage>>()
    val recentImages: LiveData<List<RecentImage>>
        get() = _recentImages

    init {
        fetchRecentImages()
    }

    private fun fetchRecentImages() {
        viewModelScope.launch {
            val getRecentImages = AstroEyeApi.retrofitService.getResponseRecentImages()
            Timber.d(getRecentImages.toString())
            _recentImages.value = getRecentImages.recentImages
        }
    }
}
