package com.kobe.feed.base

internal interface FeedContract {

    interface View {

        fun updateView()

        fun showLoading()

        fun hideLoading()
    }

    interface Presenter {

        fun bindView(view: View)

        fun unbindView()

        fun onViewCreated()
    }

    interface Interactor {

        suspend fun getList()
    }

    interface Repository {

        suspend fun fetchList()
    }
}
