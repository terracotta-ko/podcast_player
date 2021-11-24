package com.kobe.player_page.base

import com.kobe.feed_common.base.FeedCommonItemModel

internal interface PlayerPageContract {

    interface View {

        fun setCover(image: String)

        fun setTitle(title: String)

        fun showLoading()

        fun hideLoading()

        fun startPlaying(urls: List<String>, position: Int)

        fun showError(error: String)
    }

    interface Presenter {

        fun bindView(view: View)

        fun unbindView()

        fun onViewCreated(position: Int, models: List<FeedCommonItemModel>?)

        fun onMediaItemChanged(position: Int, models: List<FeedCommonItemModel>?)
    }
}
