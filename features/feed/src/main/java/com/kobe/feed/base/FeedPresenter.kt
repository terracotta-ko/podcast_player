package com.kobe.feed.base

import com.kobe.common.coroutines.CommonCoroutinesDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class FeedPresenter(
    private val repository: FeedContract.Repository,
    private val modelMapper: FeedModelMapper,
    private val dispatcher: CommonCoroutinesDispatcher
) : FeedContract.Presenter, CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = dispatcher.threadUI + job

    private var view: FeedContract.View? = null

    override fun bindView(view: FeedContract.View) {
        this.view = view
    }

    override fun unbindView() {
        view = null
    }

    override fun onViewCreated() {
        launch(dispatcher.threadUI) {
            try {
                view?.showLoading()

                val model = withContext(dispatcher.threadIO) {
                    modelMapper.toModel(repository.fetchList())
                }

                view?.run {
                    hideLoading()
                    updateView()
                }
            } catch (e: Exception) {
                view?.run {
                    hideLoading()
                    showError()
                }
            }
        }
    }
}
