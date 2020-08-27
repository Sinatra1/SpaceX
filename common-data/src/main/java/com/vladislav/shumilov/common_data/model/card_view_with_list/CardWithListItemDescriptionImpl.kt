package com.vladislav.shumilov.common_data.model.card_view_with_list

import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemDescription

data class CardWithListItemDescriptionImpl(
    override val description: String
) : CardWithListItemDescription {
    override val type = CardWithListItemTypeImpl.DESCRIPTION
}