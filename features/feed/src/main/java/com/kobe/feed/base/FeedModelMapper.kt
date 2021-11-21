package com.kobe.feed.base

import com.kobe.feed.core.FeedDomain

internal interface FeedModelMapper {

    fun toModel(domain: FeedDomain): FeedModel
}

internal class FeedModelMapperDefault : FeedModelMapper {

    override fun toModel(domain: FeedDomain): FeedModel {
        return when (domain) {
            is FeedDomain.Invalid -> FeedModel.Invalid
            is FeedDomain.Valid -> FeedModel.Valid(domain.title)
        }
    }
}
