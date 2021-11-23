package com.kobe.feed.base

import com.kobe.common.coroutines.CommonCoroutinesDispatcher
import com.kobe.common.coroutines.CommonCoroutinesDispatcherUnconfined
import com.kobe.feed.core.FeedDomain
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

internal class FeedPresenterTest {

    @MockK
    private lateinit var repository: FeedContract.Repository

    @MockK
    private lateinit var modelMapper: FeedModelMapper

    @MockK
    private lateinit var dispatcher: CommonCoroutinesDispatcher

    @MockK
    private lateinit var view: FeedContract.View

    @InjectMockKs
    private lateinit var presenter: FeedPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)

        val testDispatcher = CommonCoroutinesDispatcherUnconfined()
        every { dispatcher.threadIO } returns testDispatcher.threadIO
        every { dispatcher.threadUI } returns testDispatcher.threadUI

        presenter.bindView(view)
    }

    @Test
    fun `Given repository throws an exception, When onViewCreated, Then show an error`() {
        //>> given
        coEvery { repository.fetchList() } throws RuntimeException()

        //>> when
        presenter.onViewCreated()

        //>> then
        verify(exactly = 1) {
            view.showLoading()
            view.hideLoading()
            view.showError("something wrong")
        }
    }

    @Test
    fun `Given modelMapper returns a model, When onViewCreated, Then update the view`() {
        //>> given
        val fakeDomain = mockk<FeedDomain>(relaxed = true)
        coEvery { repository.fetchList() } returns fakeDomain

        val fakeModel = mockk<FeedModel>(relaxed = true)
        every { modelMapper.toModel(fakeDomain) } returns fakeModel

        //>> when
        presenter.onViewCreated()

        //>> then
        verify(exactly = 1) {
            view.showLoading()
            view.hideLoading()
            view.updateView(fakeModel.episodes)
        }
    }

    @Test
    fun `Given a view, When onEpisodeClicked, Then go to episode details`() {
        //>> given

        //>> when
        presenter.onEpisodeClicked(1)

        //>> then
        verify(exactly = 1) {
            view.gotoEpisodeDetails(1)
        }
    }
}
