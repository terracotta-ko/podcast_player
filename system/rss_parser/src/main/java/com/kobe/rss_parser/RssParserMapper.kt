package com.kobe.rss_parser

import com.prof.rssparser.Channel

internal interface RssParserMapper {

    fun toData(channel: Channel): RssParserData
}

internal class RssParserMapperDefault : RssParserMapper {

    override fun toData(channel: Channel): RssParserData {
        val image = channel.image?.let {
            RssParserImage(
                it.title,
                it.url,
                it.link,
                it.description
            )
        }

        val articles = channel.articles.map {
            val itunesData = it.itunesArticleData?.let { article ->
                RssParserItunesArticleData(
                    article.author,
                    article.duration,
                    article.episode,
                    article.episodeType,
                    article.explicit,
                    article.image,
                    article.keywords,
                    article.subtitle,
                    article.summary
                )
            }

            RssParserArticle(
                it.guid,
                it.title,
                it.author,
                it.link,
                it.pubDate,
                it.description,
                it.content,
                it.image,
                it.audio,
                it.video,
                it.sourceName,
                it.sourceUrl,
                it.categories,
                itunesData
            )
        }

        val ituneData = channel.itunesChannelData?.let { data ->
            val owner = data.owner?.let { user ->
                RssParserItunesOwner(user.name, user.email)
            }

            RssParserItunesChannelData(
                data.author,
                data.categories,
                data.duration,
                data.explicit,
                data.image,
                data.keywords,
                data.newsFeedUrl,
                owner,
                data.subtitle,
                data.summary,
                data.type
            )
        }

        return RssParserData(
            channel.title,
            channel.link,
            channel.description,
            image,
            channel.lastBuildDate,
            channel.updatePeriod,
            articles,
            ituneData
        )
    }
}
