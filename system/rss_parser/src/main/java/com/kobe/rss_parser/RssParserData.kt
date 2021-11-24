package com.kobe.rss_parser

data class RssParserData(
    val title: String?,
    val link: String?,
    val description: String?,
    val image: RssParserImage?,
    val lastBuildDate: String?,
    val updatePeriod: String?,
    val articles: List<RssParserArticle>,
    val itunesChannelData: RssParserItunesChannelData?
)

data class RssParserImage(
    val title: String?,
    val url: String?,
    val link: String?,
    val description: String?
)

data class RssParserArticle(
    val guid: String?,
    val title: String?,
    val author: String?,
    val link: String?,
    val pubDate: String?,
    val description: String?,
    val content: String?,
    val image: String?,
    val audio: String?,
    val video: String?,
    val sourceName: String?,
    val sourceUrl: String?,
    val categories: List<String>,
    val itunesArticleData: RssParserItunesArticleData?
)

data class RssParserItunesArticleData(
    val author: String?,
    val duration: String?,
    val episode: String?,
    val episodeType: String?,
    val explicit: String?,
    val image: String?,
    val keywords: List<String>,
    val subtitle: String?,
    val summary: String?
)

data class RssParserItunesChannelData(
    val author: String?,
    val categories: List<String> = emptyList(),
    val duration: String?,
    val explicit: String?,
    val image: String?,
    val keywords: List<String>,
    val newsFeedUrl: String?,
    val owner: RssParserItunesOwner?,
    val subtitle: String?,
    val summary: String?,
    val type: String?
)

data class RssParserItunesOwner(
    val name: String?,
    val email: String?
)
