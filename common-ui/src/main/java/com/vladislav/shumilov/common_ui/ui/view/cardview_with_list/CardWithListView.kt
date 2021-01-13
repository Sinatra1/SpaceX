package com.vladislav.shumilov.common_ui.ui.view.cardview_with_list

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.cardview.widget.CardView
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemModel
import com.vladislav.shumilov.common_ui.R
import kotlinx.android.synthetic.main.common_card_with_list_view.view.*

class CardWithListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.commonCardWithListViewTheme,
    private val adapter: CardWithListViewAbstractAdapter = CardWithListViewAdapter(context)
) : CardView(
    context,
    attrs,
    defStyleAttr
) {
    init {
        View.inflate(context, R.layout.common_card_with_list_view, this)

        cardItemsList.adapter = adapter

        val attributes =
            context.theme.obtainStyledAttributes(attrs, R.styleable.CommonCardWithListView, 0, 0)

        cardTitle.text = attributes.getString(R.styleable.CommonCardWithListView_CommonCardWithListView_title)

        attributes.recycle()
    }

    fun setItems(items: List<CardWithListItemModel>?) {
        items?.let(adapter::setItems)
    }
}