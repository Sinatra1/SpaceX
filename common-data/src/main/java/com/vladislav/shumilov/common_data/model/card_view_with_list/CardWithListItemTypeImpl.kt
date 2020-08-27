package com.vladislav.shumilov.common_data.model.card_view_with_list

import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemType

enum class CardWithListItemTypeImpl : CardWithListItemType {
    TEXT,
    LINK,
    CHECKBOX,
    DESCRIPTION,
    DIVIDER
}