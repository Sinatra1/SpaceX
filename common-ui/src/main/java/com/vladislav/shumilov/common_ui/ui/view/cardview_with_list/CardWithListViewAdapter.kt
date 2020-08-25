package com.vladislav.shumilov.common_ui.ui.view.cardview_with_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.vladislav.shumilov.common_data.model.card_view_with_list.CardWithListItemTypeImpl
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemModel
import com.vladislav.shumilov.common_ui.R
import com.vladislav.shumilov.common_ui.databinding.CardWithListViewCheckboxRowBinding
import com.vladislav.shumilov.common_ui.databinding.CardWithListViewLinkRowBinding
import com.vladislav.shumilov.common_ui.databinding.CardWithListViewTextRowBinding
import java.lang.IllegalArgumentException

internal class CardWithListViewAdapter(context: Context) :
    RecyclerView.Adapter<CardWithListViewHolder>() {

    private val items: ArrayList<CardWithListItemModel> = ArrayList()
    private val layoutInflater = LayoutInflater.from(context)
    private lateinit var binding: ViewDataBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardWithListViewHolder {
        binding =
            DataBindingUtil.inflate(
                layoutInflater,
                getContentRes(viewType),
                parent,
                false
            )

        return when (viewType) {
            CARD_WITH_LIST_TYPE_TEXT ->
                CardWithListViewTextHolder(binding as CardWithListViewTextRowBinding)
            CARD_WITH_LIST_TYPE_LINK ->
                CardWithListViewLinkHolder(binding as CardWithListViewLinkRowBinding)
            CARD_WITH_LIST_TYPE_CHECKBOX ->
                CardWithListViewCheckboxHolder(binding as CardWithListViewCheckboxRowBinding)
            else -> throw IllegalArgumentException("Illegal card with list item viewType")
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CardWithListViewHolder, position: Int) {
        val item = items[position]

        holder.bind(item)
    }

    override fun getItemViewType(position: Int) =
        when (items[position].type) {
            CardWithListItemTypeImpl.TEXT -> CARD_WITH_LIST_TYPE_TEXT
            CardWithListItemTypeImpl.LINK -> CARD_WITH_LIST_TYPE_LINK
            CardWithListItemTypeImpl.CHECKBOX -> CARD_WITH_LIST_TYPE_CHECKBOX
            else -> throw IllegalArgumentException("Illegal card with list item type")
        }

    private fun getContentRes(viewType: Int) =
        when (viewType) {
            CARD_WITH_LIST_TYPE_TEXT -> R.layout.card_with_list_view_text_row
            CARD_WITH_LIST_TYPE_LINK -> R.layout.card_with_list_view_link_row
            CARD_WITH_LIST_TYPE_CHECKBOX -> R.layout.card_with_list_view_checkbox_row
            else -> throw IllegalArgumentException("Illegal card with list item viewType")
        }
}

internal const val CARD_WITH_LIST_TYPE_TEXT = 100
internal const val CARD_WITH_LIST_TYPE_LINK = 101
internal const val CARD_WITH_LIST_TYPE_CHECKBOX = 110
