package com.kobe.feed.base

internal class FeedPresenter : FeedContract.Presenter {

    private var view: FeedContract.View? = null

    override fun bindView(view: FeedContract.View) {
        this.view = view
    }

    override fun unbindView() {
        view = null
    }

    override fun onViewCreated() {
    }
}
