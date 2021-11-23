package com.kobe.player_page.app

import android.content.Context
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.SimpleExoPlayer
import com.kobe.common.coroutines.CommonCoroutinesDispatcher
import com.kobe.common.coroutines.CommonCoroutinesDispatcherDefault
import com.kobe.player_page.base.PlayerPageContract
import com.kobe.player_page.base.PlayerPagePresenter

internal class PlayerPageServiceLocator(private val context: Context) {

    fun getPresenter(): PlayerPageContract.Presenter =
        PlayerPagePresenter(getDispatcher())

    fun getPlayer(): ExoPlayer =
        SimpleExoPlayer.Builder(context).build()

    private fun getDispatcher(): CommonCoroutinesDispatcher =
        CommonCoroutinesDispatcherDefault()
}
