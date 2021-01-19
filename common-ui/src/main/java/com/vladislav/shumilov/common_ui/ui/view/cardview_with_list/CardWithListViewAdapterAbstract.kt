package com.vladislav.shumilov.common_ui.ui.view.cardview_with_list

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemModel

abstract class CardWithListViewAdapterAbstract(context: Context) :
    RecyclerView.Adapter<CardWithListViewHolderAbstract>() {
    protected val items: ArrayList<CardWithListItemModel> = ArrayList()
    protected val layoutInflater = LayoutInflater.from(context)

    override fun getItemCount() = items.size

    abstract fun setItems(items: List<CardWithListItemModel>)
}