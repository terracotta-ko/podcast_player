package com.kobe.feed.app

import androidx.recyclerview.widget.RecyclerView
import com.kobe.common.extensions.loadImage
import com.kobe.feed.databinding.FeedItemEpisodeBinding
import com.kobe.feed_common.base.FeedCommonItemModel

internal class FeedEpisodeViewHolder(
    private val viewBinding: FeedItemEpisodeBinding,
    private val actions: FeedRecyclerViewAdapter.Actions
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(model: FeedCommonItemModel.Episode) {
        viewBinding.run {
            image.loadImage(model.image)
            title.text = model.title
            date.text = model.pubDate

            root.setOnClickListener { actions.onEpisodeClicked(absoluteAdapterPosition) }
        }
    }
}
