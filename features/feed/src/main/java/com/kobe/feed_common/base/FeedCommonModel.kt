package com.kobe.feed_common.base

sealed class FeedCommonItemModel {

    data class Cover(
        val image: String
    ) : FeedCommonItemModel()

    data class Episode(
        val title: String,
        val audio: String,
        val pubDate: String,
        val description: String,
        val image: String
    ) : FeedCommonItemModel()
}
