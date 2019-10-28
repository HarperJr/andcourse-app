package com.conceptic.andcourse.presentation.view

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout

class ScalableFitBehaviour : CoordinatorLayout.Behavior<View>() {

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        return super.onDependentViewChanged(parent, child, dependency)
    }
}