package com.vladislav.shumilov.launch_ui.ui.list

import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import com.example.launch_ui.R


class LaunchesListDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val divider = ContextCompat.getDrawable(context, R.drawable.line_divider)

    private val marginLeft =
        context.resources.getDimensionPixelSize(R.dimen.launches_small_mission_icon_size) +
                2 * context.resources.getDimensionPixelSize(R.dimen.medium_padding)

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (divider == null) {
            return
        }

        val left = parent.paddingLeft + marginLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + divider.intrinsicHeight

            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
        }
    }
}