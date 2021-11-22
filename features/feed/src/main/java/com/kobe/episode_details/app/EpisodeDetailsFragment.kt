package com.kobe.episode_details.app

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.kobe.common.extensions.loadImage
import com.kobe.episode_details.app.EpisodeDetailsFragment.Companion.EXTRA_EPISODE_POSITION
import com.kobe.episode_details.base.EpisodeDetailsContract
import com.kobe.feed.databinding.FragmentEpisodeDetailsBinding
import com.kobe.feed_common.app.FeedCommonViewModel

internal class EpisodeDetailsFragment : Fragment(), EpisodeDetailsContract.View {

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

    private lateinit var presenter: EpisodeDetailsContract.Presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val serviceLocator = EpisodeServiceLocator()
        presenter = serviceLocator.getPresenter()
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

        presenter.bindView(this)

        val position = arguments.getPosition()
        if (position > 0) {
            presenter.onViewCreated(viewModel.getEpisodes().value?.get(position))
        } else {
            presenter.onViewCreated(null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unbindView()
        viewBinding = null
    }

    //>> EpisodeDetailsContract.View

    override fun setCover(image: String) {
        viewBinding?.coverImage?.loadImage(image)
    }

    override fun setTitle(title: String) {
        viewBinding?.title?.text = title
    }

    override fun setDescription(description: String) {
        viewBinding?.description?.text = description
    }

    override fun showError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }
}

private fun Bundle.setPosition(position: Int) {
    this.putInt(EXTRA_EPISODE_POSITION, position)
}

private fun Bundle?.getPosition(): Int =
    this?.getInt(EXTRA_EPISODE_POSITION) ?: -1