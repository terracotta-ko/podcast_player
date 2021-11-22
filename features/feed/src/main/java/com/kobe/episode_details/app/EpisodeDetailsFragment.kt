package com.kobe.episode_details.app

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.kobe.episode_details.app.EpisodeDetailsFragment.Companion.EXTRA_EPISODE_POSITION
import com.kobe.feed.databinding.FragmentEpisodeDetailsBinding
import com.kobe.feed_common.app.FeedCommonViewModel

internal class EpisodeDetailsFragment : Fragment() {

    companion object {
        const val TAG = "EpisodeDetailsFragment"
        const val EXTRA_EPISODE_POSITION = "EXTRA_EPISODE_POSITION"

        fun newInstance(position: Int) =
            EpisodeDetailsFragment().apply {
                arguments = Bundle().apply {
                    setPosition(position)
                }
            }
    }

    private var viewBinding: FragmentEpisodeDetailsBinding? = null
    private val viewModel: FeedCommonViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentEpisodeDetailsBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}

private fun Bundle.setPosition(position: Int) {
    this.putInt(EXTRA_EPISODE_POSITION, position)
}

private fun Bundle?.getPosition(): Int =
    this?.getInt(EXTRA_EPISODE_POSITION) ?: -1