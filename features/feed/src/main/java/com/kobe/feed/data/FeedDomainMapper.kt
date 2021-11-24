package com.kobe.feed.data

import com.kobe.feed.core.FeedDomain
import com.kobe.rss_parser.RssParserData

internal interface FeedDomainMapper {

    fun toDomain(data: RssParserData): FeedDomain
}

internal class FeedDomainMapperDefault(
    private val episodeDomainMapper: FeedEpisodeDomainMapper
) : FeedDomainMapper {

    override fun toDomain(data: RssParserData): FeedDomain {
        val articles = data.articles.mapNotNull {
            episodeDomainMapper.toDomain(it)
        }

        return FeedDomain(data.image?.url ?: "", articles)
    }
}
