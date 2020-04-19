package com.rascarlo.astroeye.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * data class for recent image
 * parsed as object from [ResponseRecentImage.recentImages]
 * use kt annotation for parcelable
 * androidExtensions { experimental = true } in /app/build/gradle
 */
@Parcelize
data class RecentImage(
    @Json(name = "animated") val animated: Boolean?,
    @Json(name = "dec") val dec: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "h") val h: Long?,
    @Json(name = "hash") val hash: String?,
    @Json(name = "id") val id: Long?,
    @Json(name = "imaging_cameras") val imagingCameras: List<String>?,
    @Json(name = "imaging_telescopes") val imagingTelescopes: List<String>?,
    @Json(name = "is_final") val isFinal: Boolean?,
    @Json(name = "is_solved") val isSolved: Boolean?,
    @Json(name = "license") val license: Int?,
    @Json(name = "link") val link: String?,
    @Json(name = "link_to_fits") val linkToFits: String?,
    @Json(name = "locations") val locations: List<String>?,
    @Json(name = "orientation") val orientation: String?,
    @Json(name = "pixscale") val pixScale: String?,
    @Json(name = "published") val published: String?,
    @Json(name = "ra") val ra: String?,
    @Json(name = "radius") val radius: String?,
    @Json(name = "resource_uri") val resourceUri: String?,
    @Json(name = "revisions") val revisions: List<String>?,
    @Json(name = "subjects") val subjects: List<String>?,
    @Json(name = "title") val title: String?,
    @Json(name = "updated") val updated: String?,
    @Json(name = "uploaded") val uploaded: String?,
    @Json(name = "url_duckduckgo") val urlDuckDuckGo: String?,
    @Json(name = "url_duckduckgo_small") val urlDuckDuckGoSmall: String?,
    @Json(name = "url_gallery") val urlGallery: String?,
    @Json(name = "url_hd") val urlHd: String?,
    @Json(name = "url_real") val urlReal: String?,
    @Json(name = "url_regular") val urlRegular: String?,
    @Json(name = "url_thumb") val urlThumb: String?,
    @Json(name = "user") val user: String?,
    @Json(name = "w") val w: Long?
) : Parcelable