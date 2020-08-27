package com.vladislav.shumilov.common_data.model.card_view_with_list

import androidx.annotation.StringRes
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemText

data class CardWithListItemTextImpl(
    @StringRes
    override val labelRes: Int,
    override val text: String
) : CardWithListItemText {
    override val type = CardWithListItemTypeImpl.TEXT
}