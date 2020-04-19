package com.rascarlo.astroeye.recents

import androidx.paging.PageKeyedDataSource
import com.rascarlo.astroeye.network.AstroEyeApi
import com.rascarlo.astroeye.network.RecentImage

class RecentImagePagedDataSource : PageKeyedDataSource<String, RecentImage>() {

    private val api = AstroEyeApi.retrofitService

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, RecentImage>
    ) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, RecentImage>
    ) {
        val request = api.getResponseRecentImagesDeferred(offset = params.requestedLoadSize)
        TODO("Not yet implemented")
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, RecentImage>
    ) {
        TODO("Not yet implemented")
    }
}