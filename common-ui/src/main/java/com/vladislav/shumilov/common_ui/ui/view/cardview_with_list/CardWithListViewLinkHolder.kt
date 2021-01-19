package com.vladislav.shumilov.common_ui.ui.view.cardview_with_list

import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemLink
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemModel
import com.vladislav.shumilov.common_ui.databinding.CommonCardWithListViewLinkRowBinding

internal class CardWithListViewLinkHolder(private val binding: CommonCardWithListViewLinkRowBinding):
    CardWithListViewHolderAbstract(binding.root) {

    var itemLink: CardWithListItemLink? = null

    override fun bind(item: CardWithListItemModel) {
        itemLink = item as? CardWithListItemLink

        binding.viewHolder = this
        binding.executePendingBindings()
    }
}