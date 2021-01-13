package com.vladislav.shumilov.common_ui.ui.view.cardview_with_list

import androidx.annotation.DrawableRes
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemCheckbox
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemModel
import com.vladislav.shumilov.common_ui.R
import com.vladislav.shumilov.common_ui.databinding.CommonCardWithListViewCheckboxRowBinding

internal class CardWithListViewCheckboxHolder(private val binding: CommonCardWithListViewCheckboxRowBinding) :
    CardWithListViewHolder(binding.root) {

    var itemCheckbox: CardWithListItemCheckbox? = null

    @DrawableRes
    var checkboxIconRes: Int? = null

    override fun bind(item: CardWithListItemModel) {
        if (item is CardWithListItemCheckbox) {
            itemCheckbox = item
            checkboxIconRes = getCheckboxIconRes(item.isChecked)
        }

        binding.viewHolder = this
        binding.executePendingBindings()
    }

    @DrawableRes
    private fun getCheckboxIconRes(value: Boolean): Int =
        if (value) R.drawable.common_ic_checked else R.drawable.common_ic_blocked
}