package com.vladislav.shumilov.common_ui.ui.view.cardview_with_list

import androidx.recyclerview.widget.RecyclerView
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemModel

abstract class CardWithListViewAbstractAdapter : RecyclerView.Adapter<CardWithListViewHolder>() {
    abstract fun setItems(items: List<CardWithListItemModel>)
}