package com.kobe.feed.app

import android.content.Context
import com.kobe.common.coroutines.CommonCoroutinesDispatcher
import com.kobe.common.coroutines.CommonCoroutinesDispatcherDefault
import com.kobe.feed.base.*
import com.kobe.feed.data.*
import com.kobe.rss_parser.RssParser
import com.kobe.rss_parser.RssParserProvider

internal class FeedServiceLocator(private val context: Context) {

    fun getPresenter(): FeedContract.Presenter {
        return FeedPresenter(
            getRepository(),
            getModelMapper(),
            getDispatcher()
        )
    }

    fun getRecyclerViewAdapter() = FeedRecyclerViewAdapter()

    private fun getRepository(): FeedContract.Repository {
        return FeedRepository(getRssParser(), getDomainMapper())
    }

    private fun getRssParser(): RssParser {
        return RssParserProvider.getInstance(context).parser
    }

    private fun getDomainMapper(): FeedDomainMapper {
        return FeedDomainMapperDefault(getEpisodeDomainMapper())
    }

    private fun getEpisodeDomainMapper(): FeedEpisodeDomainMapper {
        return FeedEpisodeDomainMapperDefault()
    }

    private fun getModelMapper(): FeedModelMapper {
        return FeedModelMapperDefault(getItemModelMapper())
    }

    private fun getItemModelMapper(): FeedItemModelMapper {
        return FeedItemModelMapperDefault()
    }

    private fun getDispatcher(): CommonCoroutinesDispatcher {
        return CommonCoroutinesDispatcherDefault()
    }
}
