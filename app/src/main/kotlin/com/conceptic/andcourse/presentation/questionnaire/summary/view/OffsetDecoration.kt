package com.conceptic.andcourse.presentation.questionnaire.summary.view

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import com.conceptic.andcourse.support.AndroidUtils

class OffsetDecoration(offsetVertical: Int = 0, offsetHorizontal: Int = 0) : RecyclerView.ItemDecoration() {
    private val offsetVertical = AndroidUtils.dpToPx(offsetVertical)
    private val offsetHorizontal = AndroidUtils.dpToPx(offsetHorizontal)

    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        outRect.set(offsetHorizontal, offsetVertical, offsetHorizontal, offsetVertical)
    }
}
