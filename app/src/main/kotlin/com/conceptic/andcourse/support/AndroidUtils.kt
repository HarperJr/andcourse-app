package com.conceptic.andcourse.support

import android.content.res.Resources
import android.util.DisplayMetrics


object AndroidUtils {
    private val displayMetrics = Resources.getSystem().displayMetrics

    fun dpToPx(dp: Int): Int = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))

    fun pxToDp(px: Int): Int = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
}
