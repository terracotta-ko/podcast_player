package com.kobe.feed.base

import com.kobe.feed_common.base.FeedCommonItemModel

internal data class FeedModel(
    val episodes: List<FeedCommonItemModel>
)