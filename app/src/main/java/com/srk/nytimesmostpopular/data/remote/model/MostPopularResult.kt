package com.srk.nytimesmostpopular.data.remote.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by sujithrk on 25,December,2020
 * Email sujithrk89@gmail.com
 * Copyright (c) 2020 . All rights reserved.
 */

@Parcelize
data class MostPopularResult(
    @SerializedName("abstract")
    val `abstract`: String,
    @SerializedName("adx_keywords")
    val adxKeywords: String,
    @SerializedName("asset_id")
    val assetId: Long,
    @SerializedName("byline")
    val byline: String,
    @SerializedName("des_facet")
    val desFacet: List<String>,
    @SerializedName("eta_id")
    val etaId: Int,
    @SerializedName("geo_facet")
    val geoFacet: List<String>,
    @SerializedName("id")
    val id: Long,
    @SerializedName("media")
    val media: List<Media>,
    @SerializedName("nytdsection")
    val nytdsection: String,
    @SerializedName("org_facet")
    val orgFacet: List<String>,
    @SerializedName("per_facet")
    val perFacet: List<String>,
    @SerializedName("published_date")
    val publishedDate: String,
    @SerializedName("section")
    val section: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("subsection")
    val subsection: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("updated")
    val updated: String,
    @SerializedName("uri")
    val uri: String,
    @SerializedName("url")
    val url: String
): Parcelable