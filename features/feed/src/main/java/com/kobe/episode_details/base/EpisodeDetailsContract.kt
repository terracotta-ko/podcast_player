package com.kobe.episode_details.base

import com.kobe.feed_common.base.FeedCommonItemModel

internal interface EpisodeDetailsContract {

    interface View {

        fun setCover(image: String)

        fun setTitle(title: String)

        fun setDescription(description: String)

        fun showError(error: String)

        fun gotoPlayerPage(position: Int)
    }

    interface Presenter {

        fun bindView(view: View)

        fun unbindView()

        fun onViewCreated(model: FeedCommonItemModel?)

        fun onPlayIconClicked(position: Int)
    }
}