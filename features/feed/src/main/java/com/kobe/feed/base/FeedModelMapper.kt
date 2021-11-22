package com.kobe.feed.base

import com.kobe.feed.core.FeedDomain
import com.kobe.feed_common.base.FeedCommonItemModel

internal interface FeedModelMapper {

    fun toModel(domain: FeedDomain): FeedModel
}

internal class FeedModelMapperDefault(
    private val itemModelMapper: FeedItemModelMapper
) : FeedModelMapper {

    override fun toModel(domain: FeedDomain): FeedModel {
        val episodes = domain.episodes.map {
            itemModelMapper.toModel(it)
        }

        return FeedModel(listOf(FeedCommonItemModel.Cover(domain.image)) + episodes)
    }
}
