package com.rascarlo.astroeye.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * data class for recent image response
 * meta = [RecentImageMeta]
 * objects = [RecentImage]
 * use kt annotation for parcelable
 * androidExtensions { experimental = true } in /app/build/gradle
 */
@Parcelize
data class ResponseRecentImage(
    @Json(name = "meta") val recentImageMeta: RecentImageMeta,
    @Json(name = "objects") val recentImages: List<RecentImage>
) : Parcelable