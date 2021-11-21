package com.kobe.feed.base

internal sealed class FeedModel {

    object Invalid: FeedModel()

    data class Valid(
        val title: String
    ): FeedModel()
}
