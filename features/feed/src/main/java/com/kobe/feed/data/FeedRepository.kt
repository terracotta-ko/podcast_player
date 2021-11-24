package com.kobe.feed.data

import com.kobe.feed.base.FeedContract
import com.kobe.feed.core.FeedDomain
import com.kobe.rss_parser.RssParser

internal class FeedRepository(
    private val rssParser: RssParser,
    private val domainMapper: FeedDomainMapper
) : FeedContract.Repository {

    override suspend fun fetchList(): FeedDomain {
        return domainMapper.toDomain(
            rssParser.getData("https://feeds.soundcloud.com/users/soundcloud:users:322164009/sounds.rss")
        )
    }
}
