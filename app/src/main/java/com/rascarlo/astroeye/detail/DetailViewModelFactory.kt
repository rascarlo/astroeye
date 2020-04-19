package com.rascarlo.astroeye.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rascarlo.astroeye.network.RecentImage
import java.lang.IllegalArgumentException

/**
 * [ViewModelProvider.Factory] to construct [DetailViewModel]
 */
class DetailViewModelFactory(private val recentImage: RecentImage, private val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(recentImage, application) as T
        }
        throw IllegalArgumentException("Dude, that is NOT the view model class I was expecting")
    }
}