package com.rascarlo.astroeye.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * data class for recent image meta
 * parsed as meta from [ResponseRecentImage.recentImageMeta]
 * use kt annotation for parcelable
 * androidExtensions { experimental = true } in /app/build/gradle
 */
@Parcelize
data class RecentImageMeta(
    @Json(name = "limit") val limit: Long?,
    @Json(name = "next") val next: String?,
    @Json(name = "offset") val offSet: Long?,
    @Json(name = "previous") val previous: String?,
    @Json(name = "total_count") val totalCount: Long?
) : Parcelable