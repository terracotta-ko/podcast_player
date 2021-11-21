package com.kobe.feed.base

import com.kobe.feed.core.FeedDomain

internal interface FeedContract {

    interface View {

        fun updateView()

        fun showLoading()

        fun hideLoading()

        fun stopRefreshing()

        fun showError(error: String)
    }

    interface Presenter {

        fun bindView(view: View)

        fun unbindView()

        fun onViewCreated()
    }

    interface Repository {

        suspend fun fetchList(): FeedDomain
    }
}
