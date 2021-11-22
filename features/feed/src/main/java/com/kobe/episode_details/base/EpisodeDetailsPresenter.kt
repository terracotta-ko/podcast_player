package com.kobe.episode_details.base

import com.kobe.feed_common.base.FeedCommonItemModel

internal class EpisodeDetailsPresenter: EpisodeDetailsContract.Presenter {

    private var view: EpisodeDetailsContract.View? = null

    override fun bindView(view: EpisodeDetailsContract.View) {
        this.view = view
    }

    override fun unbindView() {
        view = null
    }

    override fun onViewCreated(model: FeedCommonItemModel?) {
        when(model) {
            null -> view?.showError("something wrong")
            is FeedCommonItemModel.Cover -> view?.showError("something wrong")
            is FeedCommonItemModel.Episode -> {
                view?.run {
                    setCover(model.image)
                    setTitle(model.title)
                    setDescription(model.description)
                }
            }
        }
    }
}
