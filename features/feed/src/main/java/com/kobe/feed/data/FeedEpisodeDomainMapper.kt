package com.kobe.feed.data

import com.kobe.feed.core.FeedEpisodeDomain
import com.kobe.rss_parser.RssParserArticle

internal interface FeedEpisodeDomainMapper {

    fun toDomain(dto: RssParserArticle): FeedEpisodeDomain?
}

internal class FeedEpisodeDomainMapperDefault : FeedEpisodeDomainMapper {

    override fun toDomain(dto: RssParserArticle): FeedEpisodeDomain? {
        val title = dto.title
        val link = dto.link

        return when {
            title.isNullOrBlank() -> null
            link.isNullOrBlank() -> null
            else -> {
                val pubDate = dto.pubDate?.let {
                    it.substring(0, it.indexOfFirst { c -> c == '+' })
                } ?: ""

                FeedEpisodeDomain(
                    title,
                    dto.author ?: "",
                    link,
                    pubDate,
                    dto.description ?: "",
                    dto.content ?: "",
                    dto.itunesArticleData?.image ?: "",
                )
            }
        }
    }
}