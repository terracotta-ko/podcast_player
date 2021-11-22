package com.kobe.feed.base

import com.kobe.feed.core.FeedEpisodeDomain
import com.kobe.feed_common.base.FeedCommonItemModel

internal interface FeedItemModelMapper {

    fun toModel(domain: FeedEpisodeDomain): FeedCommonItemModel
}

internal class FeedItemModelMapperDefault : FeedItemModelMapper {

    override fun toModel(domain: FeedEpisodeDomain): FeedCommonItemModel {
        return FeedCommonItemModel.Episode(
            domain.title,
            domain.author,
            domain.link,
            domain.pubDate,
            domain.description,
            domain.content,
            domain.image
        )
    }
}
