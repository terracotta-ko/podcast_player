package com.kobe.feed.app

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kobe.feed.R
import com.kobe.feed.databinding.FragmentFeedBinding

class FeedFragment : Fragment() {

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
}
