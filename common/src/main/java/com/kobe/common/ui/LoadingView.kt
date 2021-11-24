package com.kobe.common.ui

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.kobe.common.R

class LoadingView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defaultStyleAttr: Int = 0
) : LinearLayout(context, attributeSet, defaultStyleAttr) {

    private val loadingMessage: TextView

    init {
        val root = View.inflate(context, R.layout.loading_view, this)
        loadingMessage = root.findViewById(R.id.loadingMessage)

        orientation = VERTICAL
        gravity = Gravity.CENTER
        setBackgroundColor(ContextCompat.getColor(context, R.color.white_translucent_90))
    }

    fun show(message: String? = null) {
        loadingMessage.text = message
        visibility = View.VISIBLE
    }

    fun hide() {
        visibility = View.GONE
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return true
    }
}
