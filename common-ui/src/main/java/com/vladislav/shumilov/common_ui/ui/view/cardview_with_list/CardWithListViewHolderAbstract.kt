package com.vladislav.shumilov.common_ui.ui.view.cardview_with_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemModel

abstract class CardWithListViewHolderAbstract(view: View) :
    RecyclerView.ViewHolder(view) {
    abstract fun bind(item: CardWithListItemModel)
}