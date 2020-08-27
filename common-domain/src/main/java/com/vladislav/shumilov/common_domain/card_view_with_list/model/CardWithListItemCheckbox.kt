package com.vladislav.shumilov.common_domain.card_view_with_list.model

interface CardWithListItemCheckbox:
    CardWithListItemLabel {
    val isChecked: Boolean
}