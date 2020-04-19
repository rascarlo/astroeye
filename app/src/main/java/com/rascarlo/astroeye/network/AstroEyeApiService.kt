package com.rascarlo.astroeye.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import timber.log.Timber
import java.io.IOException

private const val BASE_URL = "http://www.astrobin.com/"
private const val API_KEY = "7b60031f9a32c63e6b96134059ad2ff0eb61fe70"
private const val API_SECRET = "bd8e15e92bb7e9879955567064db8624b1bb4d8a"
private const val JSON_FORMAT = "json"

/**
 * okhttp client
 * to intercept request to add [API_KEY], [API_SECRET] and [JSON_FORMAT]
 * so far d-Timber the request
 */
val okHttpClient = OkHttpClient.Builder()
    .addNetworkInterceptor(object : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            // add api key, api secret and json format
            val httpUrl: HttpUrl = chain.request().url
                .newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .addQueryParameter("api_secret", API_SECRET)
                .addQueryParameter("format", JSON_FORMAT)
                .build()
            // construct the new request
            val request: Request = chain.request().newBuilder().url(httpUrl).build()
            // log the request
            Timber.d(request.toString())
            // return the new request
            return chain.proceed(request)
        }
    })
    // build the okhttp client
    .build()

// moshi
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// retrofit
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

// api service
interface AstroEyeApiService {

    @GET("api/v1/image/?s=images")
    suspend fun getResponseRecentImages(
        @Query("offset") offset: Int = 0
    ): ResponseRecentImage
}

// api
object AstroEyeApi {
    val retrofitService: AstroEyeApiService by lazy {
        retrofit.create(AstroEyeApiService::class.java)
    }
}
