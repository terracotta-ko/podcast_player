package com.kobe.feed.data

import com.kobe.feed.core.FeedDomain
import com.kobe.rss_parser.RssParserData

internal interface FeedDomainMapper {

    fun toDomain(data: RssParserData): FeedDomain
}

internal class FeedDomainMapperDefault : FeedDomainMapper {

    override fun toDomain(data: RssParserData): FeedDomain {
        val channelLink = data.link
        return if (channelLink.isNullOrBlank()) {
            FeedDomain.Invalid
        } else {
            FeedDomain.Valid(
                data.title ?: "",
                channelLink
            )
        }
    }
}
