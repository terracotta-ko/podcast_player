package com.kobe.feed_common.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kobe.feed_common.base.FeedCommonItemModel

internal class FeedCommonViewModel : ViewModel() {
    private val episodes: MutableLiveData<List<FeedCommonItemModel>> by lazy {
        MutableLiveData<List<FeedCommonItemModel>>()
    }

    fun update(episodes: List<FeedCommonItemModel>) {
        this.episodes.value = episodes
    }

    fun getEpisodes(): LiveData<List<FeedCommonItemModel>> {
        return episodes
    }
}
