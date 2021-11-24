package com.kobe.player_page.base

import com.kobe.common.coroutines.CommonCoroutinesDispatcher
import com.kobe.feed_common.base.FeedCommonItemModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class PlayerPagePresenter(
    private val dispatcher: CommonCoroutinesDispatcher
) : PlayerPageContract.Presenter, CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = dispatcher.threadUI + job

    private var view: PlayerPageContract.View? = null

    override fun bindView(view: PlayerPageContract.View) {
        this.view = view
    }

    override fun unbindView() {
        this.view = null
    }

    override fun onViewCreated(position: Int, models: List<FeedCommonItemModel>?) {
        when {
            position < 1 -> view?.showError("something wrong")
            models == null -> view?.showError("something wrong")
            else -> {
                when (val model = models[position]) {
                    is FeedCommonItemModel.Cover -> view?.showError("something wrong")
                    is FeedCommonItemModel.Episode -> {
                        launch(dispatcher.threadUI) {
                            view?.showLoading()

                            val playList = withContext(dispatcher.threadIO) {
                                models.filterIsInstance<FeedCommonItemModel.Episode>()
                                    .map { it.audio }
                                    .reversed()
                            }

                            val currentPosition = if(models.first() is FeedCommonItemModel.Cover) {
                                playList.lastIndex - position + 1
                            } else {
                                playList.lastIndex - position
                            }
                            view?.run {
                                hideLoading()
                                startPlaying(playList, currentPosition)
                                setCover(model.image)
                                setTitle(model.title)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onMediaItemChanged(position: Int, models: List<FeedCommonItemModel>?) {
        if (models == null) {
            view?.showError("something wrong")
        } else {
            val model = models[models.lastIndex - position]
            if(model is FeedCommonItemModel.Episode) {
                view?.run {
                    setCover(model.image)
                    setTitle(model.title)
                }
            }
        }
    }
}
