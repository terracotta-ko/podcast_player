package com.kobe.feed.app

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kobe.feed.base.FeedContract
import com.kobe.feed.databinding.FragmentFeedBinding
import com.kobe.feed_common.app.FeedCommonViewModel
import com.kobe.feed_common.base.FeedCommonItemModel

class FeedFragment :
    Fragment(),
    FeedContract.View {

    companion object {
        fun newInstance() = FeedFragment()
    }

    private var viewBinding: FragmentFeedBinding? = null
    private val viewModel: FeedCommonViewModel by activityViewModels()

    private lateinit var presenter: FeedContract.Presenter
    private lateinit var recyclerViewAdapter: FeedRecyclerViewAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val serviceLocator = FeedServiceLocator(context)
        presenter = serviceLocator.getPresenter()
        recyclerViewAdapter = serviceLocator.getRecyclerViewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentFeedBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupViewModel()

        presenter.bindView(this)
        presenter.onViewCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unbindView()
        viewBinding = null
    }

    //>> FeedContract.View

    override fun updateView(episodes: List<FeedCommonItemModel>) {
        viewModel.update(episodes)
    }

    override fun showLoading() {
        viewBinding?.loadingView?.show()
    }

    override fun hideLoading() {
        viewBinding?.loadingView?.hide()
    }

    override fun showError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    //>> private functions

    private fun setupRecyclerView() {
        viewBinding?.run {
            val linearLayoutManager = LinearLayoutManager(requireContext())
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL


            recyclerView.layoutManager = linearLayoutManager
            recyclerView.adapter = recyclerViewAdapter
        }
    }

    private fun setupViewModel() {
        viewModel.getEpisodes().observe(
            viewLifecycleOwner
        ) { episodes ->
            recyclerViewAdapter.updateView(episodes)
        }
    }
}
