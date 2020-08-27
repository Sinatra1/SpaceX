package com.vladislav.shumilov.common_data.model.card_view_with_list

import androidx.annotation.StringRes
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemLink

data class CardWithListItemLinkImpl(
    @StringRes
    override val labelRes: Int,
    override val text: String,
    override val uri: String? = null
) : CardWithListItemLink {
    override val type = CardWithListItemTypeImpl.LINK
}