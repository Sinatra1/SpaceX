package com.vladislav.shumilov.common_data.model.card_view_with_list

import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemLink

data class CardWithListItemLinkImpl(
    override val title: String,
    override val text: String,
    override val uri: String
) : CardWithListItemLink {
    override val type = CardWithListItemTypeImpl.LINK
}