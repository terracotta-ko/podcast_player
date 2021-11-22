package com.kobe.feed.data

import android.util.Log
import com.kobe.feed.core.FeedEpisodeDomain
import com.kobe.rss_parser.RssParserArticle

internal interface FeedEpisodeDomainMapper {

    fun toDomain(dto: RssParserArticle): FeedEpisodeDomain?
}

internal class FeedEpisodeDomainMapperDefault : FeedEpisodeDomainMapper {

    override fun toDomain(dto: RssParserArticle): FeedEpisodeDomain? {
        val title = dto.title
        val audio = dto.audio

        return when {
            title.isNullOrBlank() -> null
            audio.isNullOrBlank() -> null
            else -> {
                val pubDate = dto.pubDate?.let {
                    it.substring(0, it.indexOfFirst { c -> c == '+' })
                } ?: ""

                FeedEpisodeDomain(
                    title,
                    dto.author ?: "",
                    audio,
                    pubDate,
                    dto.description ?: "",
                    dto.itunesArticleData?.image ?: "",
                )
            }
        }
    }
}