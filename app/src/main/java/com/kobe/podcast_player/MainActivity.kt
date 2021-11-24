package com.kobe.podcast_player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kobe.main_page.app.FeedMainPageFragment
import com.kobe.podcast_player.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, FeedMainPageFragment.newInstance())
                .commit()
        }
    }
}
