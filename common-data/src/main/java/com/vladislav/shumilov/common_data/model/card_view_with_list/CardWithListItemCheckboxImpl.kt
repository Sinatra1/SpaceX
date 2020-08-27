package com.vladislav.shumilov.common_data.model.card_view_with_list

import androidx.annotation.StringRes
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemCheckbox

data class CardWithListItemCheckboxImpl(
    @StringRes
    override val labelRes: Int,
    override val isChecked: Boolean = false
) : CardWithListItemCheckbox {
    override val type = CardWithListItemTypeImpl.CHECKBOX
}