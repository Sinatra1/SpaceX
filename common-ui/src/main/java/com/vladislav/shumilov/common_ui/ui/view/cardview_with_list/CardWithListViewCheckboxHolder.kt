package com.vladislav.shumilov.common_ui.ui.view.cardview_with_list

import androidx.databinding.ObservableField
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemCheckbox
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemModel
import com.vladislav.shumilov.common_ui.databinding.CardWithListViewCheckboxRowBinding

internal class CardWithListViewCheckboxHolder(private val binding: CardWithListViewCheckboxRowBinding) :
    CardWithListViewHolder(binding.root) {

    val itemCheckbox = ObservableField<CardWithListItemCheckbox>()

    override fun bind(item: CardWithListItemModel) {
        itemCheckbox.set(item as CardWithListItemCheckbox)

        binding.viewHolder = this
        binding.executePendingBindings()
    }
}