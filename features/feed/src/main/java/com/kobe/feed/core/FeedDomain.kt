package com.kobe.feed.core

internal data class FeedDomain(
    val image: String,
    val episodes: List<FeedEpisodeDomain>
)

data class FeedEpisodeDomain(
    val title: String,
    val author: String,
    val link: String,
    val pubDate: String,
    val description: String,
    val content: String,
    val image: String
)
