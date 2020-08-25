package com.vladislav.shumilov.common_data.model.card_view_with_list

import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemCheckbox

data class CardWithListItemCheckboxImpl(
    override val title: String,
    override val checked: Boolean = false
): CardWithListItemCheckbox {
    override val type = CardWithListItemTypeImpl.CHECKBOX
}