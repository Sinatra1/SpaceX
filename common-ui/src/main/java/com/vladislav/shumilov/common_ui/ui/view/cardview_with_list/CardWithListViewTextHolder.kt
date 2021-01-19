package com.vladislav.shumilov.common_ui.ui.view.cardview_with_list

import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemModel
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemText
import com.vladislav.shumilov.common_ui.databinding.CommonCardWithListViewTextRowBinding

internal class CardWithListViewTextHolder(private val binding: CommonCardWithListViewTextRowBinding) :
    CardWithListViewHolderAbstract(binding.root) {

    var itemText: CardWithListItemText? = null

    override fun bind(item: CardWithListItemModel) {
        itemText = item as? CardWithListItemText

        binding.viewHolder = this
        binding.executePendingBindings()
    }
}