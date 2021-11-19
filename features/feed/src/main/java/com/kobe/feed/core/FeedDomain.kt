package com.kobe.feed.core

internal sealed class FeedDomain {

    object Invalid : FeedDomain()

    data class Valid(
        val title: String?,
        val link: String
    ) : FeedDomain()
}
