package com.rascarlo.astroeye.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rascarlo.astroeye.network.RecentImage

/**
 * view model to hold [RecentImage] for [DetailFragment]
 * use [AndroidViewModel] to get application and use it for resource context
 */
class DetailViewModel(recentImage: RecentImage, application: Application) :
    AndroidViewModel(application) {


    private val _recentImage = MutableLiveData<RecentImage>()
    val recentImage: LiveData<RecentImage>
        get() = _recentImage

    init {
        _recentImage.value = recentImage
    }
}