package com.vladislav.shumilov.common_ui.ui.view.cardview_with_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.vladislav.shumilov.common_data.model.card_view_with_list.CardWithListItemTypeImpl
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemModel
import com.vladislav.shumilov.common_ui.R
import com.vladislav.shumilov.common_ui.databinding.*

internal class CardWithListViewAdapter(context: Context) : CardWithListViewAdapterAbstract(context) {

    private lateinit var binding: ViewDataBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardWithListViewHolderAbstract {
        binding =
            DataBindingUtil.inflate(
                layoutInflater,
                getContentRes(viewType),
                parent,
                false
            )

        return when (viewType) {
            CARD_WITH_LIST_TYPE_TEXT ->
                CardWithListViewTextHolder(binding as CommonCardWithListViewTextRowBinding)
            CARD_WITH_LIST_TYPE_LINK ->
                CardWithListViewLinkHolder(binding as CommonCardWithListViewLinkRowBinding)
            CARD_WITH_LIST_TYPE_CHECKBOX ->
                CardWithListViewCheckboxHolder(binding as CommonCardWithListViewCheckboxRowBinding)
            CARD_WITH_LIST_TYPE_DESCRIPTION ->
                CardWithListViewDescriptionHolder(binding as CommonCardWithListViewDescriptionRowBinding)
            CARD_WITH_LIST_TYPE_DIVIDER ->
                CardWithListViewDividerHolder(binding as CommonCardWithListViewDividerRowBinding)
            else -> throw IllegalArgumentException("Illegal card with list item viewType")
        }
    }

    override fun onBindViewHolder(holder: CardWithListViewHolderAbstract, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int) =
        when (items[position].type) {
            CardWithListItemTypeImpl.TEXT -> CARD_WITH_LIST_TYPE_TEXT
            CardWithListItemTypeImpl.LINK -> CARD_WITH_LIST_TYPE_LINK
            CardWithListItemTypeImpl.CHECKBOX -> CARD_WITH_LIST_TYPE_CHECKBOX
            CardWithListItemTypeImpl.DESCRIPTION -> CARD_WITH_LIST_TYPE_DESCRIPTION
            CardWithListItemTypeImpl.DIVIDER -> CARD_WITH_LIST_TYPE_DIVIDER
            else -> throw IllegalArgumentException("Illegal card with list item type")
        }

    override fun setItems(items: List<CardWithListItemModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    @LayoutRes
    private fun getContentRes(viewType: Int) =
        when (viewType) {
            CARD_WITH_LIST_TYPE_TEXT -> R.layout.common_card_with_list_view_text_row
            CARD_WITH_LIST_TYPE_LINK -> R.layout.common_card_with_list_view_link_row
            CARD_WITH_LIST_TYPE_CHECKBOX -> R.layout.common_card_with_list_view_checkbox_row
            CARD_WITH_LIST_TYPE_DESCRIPTION -> R.layout.common_card_with_list_view_description_row
            CARD_WITH_LIST_TYPE_DIVIDER -> R.layout.common_card_with_list_view_divider_row
            else -> throw IllegalArgumentException("Illegal card with list item viewType")
        }
}

internal const val CARD_WITH_LIST_TYPE_TEXT = 100
internal const val CARD_WITH_LIST_TYPE_LINK = 101
internal const val CARD_WITH_LIST_TYPE_CHECKBOX = 102
internal const val CARD_WITH_LIST_TYPE_DESCRIPTION = 103
internal const val CARD_WITH_LIST_TYPE_DIVIDER = 104
