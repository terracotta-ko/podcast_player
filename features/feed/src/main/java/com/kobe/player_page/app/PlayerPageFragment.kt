package com.kobe.player_page.app

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.kobe.common.extensions.loadImage
import com.kobe.episode_details.app.getPosition
import com.kobe.episode_details.app.setPosition
import com.kobe.feed.databinding.FragmentPlayerPageBinding
import com.kobe.feed_common.app.FeedCommonViewModel
import com.kobe.player_page.base.PlayerPageContract

internal class PlayerPageFragment : Fragment(), PlayerPageContract.View {

    companion object {
        const val TAG = "PlayerPageFragment"

        fun newInstance(position: Int) =
            PlayerPageFragment().apply {
                arguments = Bundle().apply {
                    setPosition(position)
                }
            }
    }

    private var viewBinding: FragmentPlayerPageBinding? = null
    private val viewModel: FeedCommonViewModel by activityViewModels()

    private lateinit var player: ExoPlayer
    private lateinit var presenter: PlayerPageContract.Presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val serviceLocator = PlayerPageServiceLocator(context)
        presenter = serviceLocator.getPresenter()
        player = serviceLocator.getPlayer()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentPlayerPageBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupControlPanel()

        presenter.bindView(this)

        presenter.onViewCreated(
            arguments.getPosition(),
            viewModel.getEpisodes().value
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
        player.release()
    }

    //>> PlayerPageContract.View

    override fun setCover(image: String) {
        viewBinding?.coverImage?.loadImage(image)
    }

    override fun setTitle(title: String) {
        viewBinding?.title?.text = title
    }

    override fun startPlaying(urls: List<String>, position: Int) {
        val items = urls.map { MediaItem.fromUri(it) }
        player.addMediaItems(items)
        player.prepare()
        player.seekTo(position, 0)
        player.play()
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

    private fun setupControlPanel() {
        player.addListener(object : Player.Listener {
            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                super.onMediaItemTransition(mediaItem, reason)
                presenter.onMediaItemChanged(
                    player.currentWindowIndex,
                    viewModel.getEpisodes().value
                )
            }
        })

        viewBinding?.controlPanel?.player = player
    }
}
