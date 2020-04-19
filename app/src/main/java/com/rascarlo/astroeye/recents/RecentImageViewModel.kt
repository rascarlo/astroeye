package com.rascarlo.astroeye.recents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rascarlo.astroeye.network.AstroEyeApi
import com.rascarlo.astroeye.network.RecentImage
import com.rascarlo.astroeye.network.ResponseRecentImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * view model for the [RecentImagesFragment]
 * use coroutine for [getResponse] to process result from [AstroEyeApi.retrofitService]
 */
class RecentImageViewModel : ViewModel() {
    // coroutine job
    private val viewModelJob = Job()

    // coroutine scope
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    // response
    private val _responseRecentImages = MutableLiveData<ResponseRecentImage>()

    /**
     * recent images response
     * use [_recentImages] for internal use
     * use [recentImages] to expose the live data
     */
    private val _recentImages = MutableLiveData<List<RecentImage>>()
    val recentImages: LiveData<List<RecentImage>>
        get() = _recentImages

    /**
     * on initialization, populate the live data
     */
    init {
        getResponse()
    }

    private fun getResponse() {
        coroutineScope.launch {
            var getRecentImagesDeferred = AstroEyeApi.retrofitService.getResponseRecentImagesDeferred(0)
            try {
                val response = getRecentImagesDeferred.await()
                _responseRecentImages.value = response
                Timber.d(response.toString())
                when {
                    response.recentImages.isNotEmpty() -> {
                        _recentImages.value = response.recentImages
                    }
                    else -> {
                        _recentImages.value = null
                    }
                }
            } catch (e: Exception) {
                Timber.e(e)
                _responseRecentImages.value = null
                _recentImages.value = null
            }
        }
    }

    /**
     * be sure to cancel [viewModelJob] on cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}