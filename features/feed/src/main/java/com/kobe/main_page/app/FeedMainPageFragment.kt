package com.kobe.main_page.app

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.kobe.common.extensions.withFragmentActivity
import com.kobe.episode_details.app.EpisodeDetailsFragment
import com.kobe.feed.R
import com.kobe.feed.app.FeedFragment

class FeedMainPageFragment :
    Fragment(R.layout.fragment_feed_main_page) {

    companion object {
        fun newInstance() = FeedMainPageFragment()
    }

    private var onBackPressedCallback: OnBackPressedCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    FeedFragment.newInstance(),
                    FeedFragment.TAG
                )
                .commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBackPressedDispatcher()
    }

    //>> public functions

    fun gotoEpisodeDetails(position: Int) {
        childFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                EpisodeDetailsFragment.newInstance(position),
                EpisodeDetailsFragment.TAG
            )
            .addToBackStack(EpisodeDetailsFragment.TAG)
            .commit()
    }

    //>> private functions

    private fun setupBackPressedDispatcher() {
        val newOnBackPressedCallback = onBackPressedCallback
            ?: object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (childFragmentManager.backStackEntryCount > 0) {
                        childFragmentManager.popBackStack()
                    } else {
                        activity?.finish()
                    }
                }
            }

        onBackPressedCallback = newOnBackPressedCallback
        withFragmentActivity {
            it.onBackPressedDispatcher.addCallback(viewLifecycleOwner, newOnBackPressedCallback)
        }
    }
}