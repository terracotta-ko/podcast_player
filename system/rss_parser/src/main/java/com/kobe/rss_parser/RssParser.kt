package com.kobe.rss_parser

import android.content.Context
import com.prof.rssparser.Parser

interface RssParser {

    suspend fun getData(url: String): RssParserData
}

class RssParserProvider private constructor(context: Context) {

    val parser: RssParser by lazy {
        RssParserDefault(
            Parser.Builder()
                .context(context)
                .cacheExpirationMillis(24L * 60L * 60L * 100L) // one day
                .build(),
            RssParserMapperDefault()
        )
    }

    companion object {
        @Volatile
        private var INSTANCE: RssParserProvider? = null

        fun getInstance(context: Context): RssParserProvider {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: RssParserProvider(context).also { INSTANCE = it }
            }
        }
    }
}

internal class RssParserDefault(
    private val parser: Parser,
    private val mapper: RssParserMapper
) : RssParser {

    override suspend fun getData(url: String): RssParserData {
        return mapper.toData(parser.getChannel(url))
    }
}
