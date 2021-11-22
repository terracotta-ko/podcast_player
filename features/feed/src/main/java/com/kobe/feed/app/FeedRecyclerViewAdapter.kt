package com.kobe.feed.app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kobe.feed.databinding.FeedItemCoverBinding
import com.kobe.feed.databinding.FeedItemEpisodeBinding
import com.kobe.feed_common.base.FeedCommonItemModel

internal class FeedRecyclerViewAdapter(
    private val actions: Actions
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface Actions {

        fun onEpisodeClicked(position: Int)
    }

    companion object {
        private const val VIEW_TYPE_COVER = 0
        private const val VIEW_TYPE_EPISODE = 1
    }

    private var models: List<FeedCommonItemModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_COVER -> {
                FeedCoverViewHolder(
                    FeedItemCoverBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                FeedEpisodeViewHolder(
                    FeedItemEpisodeBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    actions
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val model = models[position]) {
            is FeedCommonItemModel.Cover -> {
                (holder as FeedCoverViewHolder).bind(model)
            }
            is FeedCommonItemModel.Episode -> {
                (holder as FeedEpisodeViewHolder).bind(model)
            }
        }
    }

    override fun getItemCount(): Int = models.size

    override fun getItemViewType(position: Int): Int {
        return when (models[position]) {
            is FeedCommonItemModel.Cover -> VIEW_TYPE_COVER
            is FeedCommonItemModel.Episode -> VIEW_TYPE_EPISODE
        }
    }

    fun updateView(models: List<FeedCommonItemModel>) {
        this.models = models
        notifyDataSetChanged()
    }
}
