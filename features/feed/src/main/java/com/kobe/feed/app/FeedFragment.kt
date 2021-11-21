package com.kobe.feed.app

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kobe.feed.base.FeedContract
import com.kobe.feed.databinding.FragmentFeedBinding

class FeedFragment :
    Fragment(),
    FeedContract.View {

    companion object {
        fun newInstance() = FeedFragment()
    }

    private var viewBinding: FragmentFeedBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    //>> FeedContract.View

    override fun updateView() {
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun stopRefreshing() {
        TODO("Not yet implemented")
    }

    override fun showError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }
}
