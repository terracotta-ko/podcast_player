package com.kobe.episode_details.app

import com.kobe.episode_details.base.EpisodeDetailsContract
import com.kobe.episode_details.base.EpisodeDetailsPresenter

internal class EpisodeServiceLocator {

    fun getPresenter(): EpisodeDetailsContract.Presenter =
        EpisodeDetailsPresenter()
}