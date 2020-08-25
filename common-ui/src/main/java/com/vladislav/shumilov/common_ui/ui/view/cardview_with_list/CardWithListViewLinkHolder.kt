package com.vladislav.shumilov.common_ui.ui.view.cardview_with_list

import androidx.databinding.ObservableField
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemLink
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemModel
import com.vladislav.shumilov.common_ui.databinding.CardWithListViewLinkRowBinding

internal class CardWithListViewLinkHolder(private val binding: CardWithListViewLinkRowBinding):
    CardWithListViewHolder(binding.root) {

    val itemLink = ObservableField<CardWithListItemLink>()

    override fun bind(item: CardWithListItemModel) {
        itemLink.set(item as CardWithListItemLink)

        binding.viewHolder = this
        binding.executePendingBindings()
    }
}