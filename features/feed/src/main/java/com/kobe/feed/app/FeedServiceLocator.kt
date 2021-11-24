package com.kobe.feed.app

import android.content.Context
import com.kobe.common.coroutines.CommonCoroutinesDispatcher
import com.kobe.common.coroutines.CommonCoroutinesDispatcherDefault
import com.kobe.feed.base.*
import com.kobe.feed.data.*
import com.kobe.rss_parser.RssParser
import com.kobe.rss_parser.RssParserProvider

internal class FeedServiceLocator(private val context: Context) {

    fun getPresenter(): FeedContract.Presenter =
        FeedPresenter(
            getRepository(),
            getModelMapper(),
            getDispatcher()
        )

    fun getRecyclerViewAdapter(actions: FeedRecyclerViewAdapter.Actions) =
        FeedRecyclerViewAdapter(actions)

    private fun getRepository(): FeedContract.Repository =
        FeedRepository(getRssParser(), getDomainMapper())

    private fun getRssParser(): RssParser =
        RssParserProvider.getInstance(context).parser

    private fun getDomainMapper(): FeedDomainMapper =
        FeedDomainMapperDefault(getEpisodeDomainMapper())

    private fun getEpisodeDomainMapper(): FeedEpisodeDomainMapper =
        FeedEpisodeDomainMapperDefault()

    private fun getModelMapper(): FeedModelMapper =
        FeedModelMapperDefault(getItemModelMapper())

    private fun getItemModelMapper(): FeedItemModelMapper =
        FeedItemModelMapperDefault()

    private fun getDispatcher(): CommonCoroutinesDispatcher =
        CommonCoroutinesDispatcherDefault()
}
