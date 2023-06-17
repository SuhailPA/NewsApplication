package com.example.newsapp.data.newslist_response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import androidx.versionedparcelable.VersionedParcelize

@Entity
@Parcelize
data class Article(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
):Parcelable