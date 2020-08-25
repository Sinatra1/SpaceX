package com.vladislav.shumilov.common_data.model.card_view_with_list

import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemText

data class CardWithListItemTextImpl(
    override val title: String,
    override val text: String
) : CardWithListItemText {
    override val type = CardWithListItemTypeImpl.TEXT
}