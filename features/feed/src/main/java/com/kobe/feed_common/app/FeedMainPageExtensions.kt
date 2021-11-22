package com.kobe.feed_common.app

import androidx.fragment.app.Fragment
import com.kobe.main_page.app.FeedMainPageFragment

internal inline fun Fragment.withMagePageFragment(block: (FeedMainPageFragment) -> Unit) {
    (parentFragment as? FeedMainPageFragment)?.run(block)
}
