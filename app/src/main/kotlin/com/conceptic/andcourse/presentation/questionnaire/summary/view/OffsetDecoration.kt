package com.conceptic.andcourse.presentation.questionnaire.summary.view

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import com.conceptic.andcourse.support.AndroidUtils

class OffsetDecoration(offsetBottomDp: Int = 0, offsetAsideDp: Int = 0) : RecyclerView.ItemDecoration() {
    private val offsetBottomPx = AndroidUtils.dpToPx(offsetBottomDp)
    private val offsetAsidePx = AndroidUtils.dpToPx(offsetAsideDp)

    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        parent.adapter?.let { adapter ->
            if (itemPosition != 0 || itemPosition != adapter.itemCount)
                outRect.set(offsetAsidePx, offsetBottomPx, offsetAsidePx, 0)
        } ?: throw IllegalStateException("Adapter is undefined")
    }
}
