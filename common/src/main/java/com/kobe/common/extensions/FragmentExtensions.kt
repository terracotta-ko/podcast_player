package com.kobe.common.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

inline fun Fragment.withFragmentActivity(block: (FragmentActivity) -> Unit) {
    activity?.let(block)
}
