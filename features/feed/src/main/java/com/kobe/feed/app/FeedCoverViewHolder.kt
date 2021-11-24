package com.kobe.feed.app

import androidx.recyclerview.widget.RecyclerView
import com.kobe.common.extensions.loadImage
import com.kobe.feed.databinding.FeedItemCoverBinding
import com.kobe.feed_common.base.FeedCommonItemModel

internal class FeedCoverViewHolder(
    private val viewBinding: FeedItemCoverBinding
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(model: FeedCommonItemModel.Cover) {
        viewBinding.run {
            cover.loadImage(model.image)
        }
    }
}
