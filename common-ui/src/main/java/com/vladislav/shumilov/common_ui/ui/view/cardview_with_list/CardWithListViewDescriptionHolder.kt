package com.vladislav.shumilov.common_ui.ui.view.cardview_with_list

import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemDescription
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemModel
import com.vladislav.shumilov.common_ui.databinding.CommonCardWithListViewDescriptionRowBinding

class CardWithListViewDescriptionHolder(private val binding: CommonCardWithListViewDescriptionRowBinding) :
    CardWithListViewHolder(binding.root) {

    var itemDescription: CardWithListItemDescription? = null

    override fun bind(item: CardWithListItemModel) {
        itemDescription = item as? CardWithListItemDescription

        binding.viewHolder = this
        binding.executePendingBindings()
    }
}