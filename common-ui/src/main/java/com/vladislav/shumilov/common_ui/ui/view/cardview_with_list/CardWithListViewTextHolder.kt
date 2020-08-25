package com.vladislav.shumilov.common_ui.ui.view.cardview_with_list

import androidx.databinding.ObservableField
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemLink
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemModel
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemText
import com.vladislav.shumilov.common_ui.databinding.CardWithListViewTextRowBinding

internal class CardWithListViewTextHolder(private val binding: CardWithListViewTextRowBinding) :
    CardWithListViewHolder(binding.root) {

    val itemText = ObservableField<CardWithListItemText>()

    override fun bind(item: CardWithListItemModel) {
        itemText.set(item as CardWithListItemLink)

        binding.viewHolder = this
        binding.executePendingBindings()
    }
}