package com.vladislav.shumilov.common_ui.ui.view.cardview_with_list

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.view.ContextThemeWrapper
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.vladislav.shumilov.common_ui.R
import com.vladislav.shumilov.core_ui.utils.getDataFromAttrOrNull
import kotlinx.android.synthetic.main.card_with_list_view.view.*

class CardWithListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.cardWithListViewTheme,
    private val adapter: RecyclerView.Adapter<*> = CardWithListViewAdapter(context)
) : CardView(
    ContextThemeWrapper(
        context,
        context.getDataFromAttrOrNull(defStyleAttr) ?: R.style.CardWithListView
    ),
    attrs,
    defStyleAttr
) {
    init {
        View.inflate(context, R.layout.card_with_list_view, this)

        cardItemsList.adapter = adapter

        val attributes =
            context.theme.obtainStyledAttributes(attrs, R.styleable.CardWithListView, 0, 0)
    }
}