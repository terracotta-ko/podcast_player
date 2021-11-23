package com.kobe.feed.data

import com.kobe.feed.core.FeedEpisodeDomain
import com.kobe.rss_parser.RssParserArticle
import com.kobe.rss_parser.RssParserItunesArticleData
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

internal class FeedEpisodeDomainMapperDefaultTest {

    @InjectMockKs
    private lateinit var mapper: FeedEpisodeDomainMapperDefault

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun `Given title is null, When toDomain, Then return null`() {
        //>> given
        val dto = mockk<RssParserArticle>(relaxed = true)
        every { dto.title } returns null
        every { dto.audio } returns "audio"

        //>> when
        val result = mapper.toDomain(dto)

        //>> then
        assertNull(result)
    }

    @Test
    fun `Given title is blank, When toDomain, Then return null`() {
        //>> given
        val dto = mockk<RssParserArticle>(relaxed = true)
        every { dto.title } returns " "
        every { dto.audio } returns "audio"

        //>> when
        val result = mapper.toDomain(dto)

        //>> then
        assertNull(result)
    }

    @Test
    fun `Given audio is null, When toDomain, Then return null`() {
        //>> given
        val dto = mockk<RssParserArticle>(relaxed = true)
        every { dto.title } returns "title"
        every { dto.audio } returns null

        //>> when
        val result = mapper.toDomain(dto)

        //>> then
        assertNull(result)
    }

    @Test
    fun `Given audio is blank, When toDomain, Then return null`() {
        //>> given
        val dto = mockk<RssParserArticle>(relaxed = true)
        every { dto.title } returns "title"
        every { dto.audio } returns " "

        //>> when
        val result = mapper.toDomain(dto)

        //>> then
        assertNull(result)
    }

    @Test
    fun `Given dto is valid, When toDomain, Then return a domain`() {
        //>> given
        val dto = mockk<RssParserArticle>(relaxed = true)
        every { dto.title } returns "title"
        every { dto.audio } returns "audio"
        every { dto.pubDate } returns "2021 +0000"
        every { dto.pubDate } returns "2021 +0000"
        every { dto.description } returns "description"

        val fakeItunesArticleData = mockk<RssParserItunesArticleData>(relaxed = true)
        every { fakeItunesArticleData.image } returns "image"
        every { dto.itunesArticleData } returns fakeItunesArticleData

        //>> when
        val result = mapper.toDomain(dto)

        //>> then
        val expectedResult = FeedEpisodeDomain(
            "title",
            "audio",
            "2021 ",
            "description",
            "image"
        )
        assertEquals(expectedResult, result)
    }
}
