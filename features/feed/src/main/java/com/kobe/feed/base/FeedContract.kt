package com.kobe.feed.base

import com.kobe.feed.core.FeedDomain
import com.kobe.feed_common.base.FeedCommonItemModel

internal interface FeedContract {

    interface View {

        fun updateView(episodes: List<FeedCommonItemModel>)

        fun showLoading()

        fun hideLoading()

        fun showError(error: String)

        fun gotoEpisodeDetails(position: Int)
    }

    interface Presenter {

        fun bindView(view: View)

        fun unbindView()

        fun onViewCreated()

        fun onEpisodeClicked(position: Int)
    }

    interface Repository {

        suspend fun fetchList(): FeedDomain
    }
}
