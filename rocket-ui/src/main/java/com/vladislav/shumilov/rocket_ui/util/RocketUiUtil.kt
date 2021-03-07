package com.vladislav.shumilov.rocket_ui.util

import com.vladislav.shumilov.rocket_domain.model.local.Core
import com.vladislav.shumilov.common_data.model.card_view_with_list.CardWithListItemCheckboxImpl
import com.vladislav.shumilov.common_data.model.card_view_with_list.CardWithListItemLinkImpl
import com.vladislav.shumilov.common_data.model.card_view_with_list.CardWithListItemTextImpl
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemModel
import com.vladislav.shumilov.rocket_ui.R

fun getCoreCardViewItems(
    core: Core
): MutableList<CardWithListItemModel> {
    val details = mutableListOf<CardWithListItemModel>()

    core.serial?.let {
        details.add(CardWithListItemLinkImpl(R.string.rockets_core_serial, it))
    }

    core.landingVehicle?.let {
        details.add(CardWithListItemTextImpl(R.string.rockets_core_model, it))
    }

    details.add(CardWithListItemCheckboxImpl(R.string.rockets_reused, core.reused))

    core.block?.let {
        details.add(CardWithListItemLinkImpl(R.string.rockets_landing_zone, it))
    }

    details.add(
        CardWithListItemCheckboxImpl(
            R.string.rockets_landing_success,
            core.landSuccess ?: false
        )
    )

    details.add(
        CardWithListItemCheckboxImpl(
            R.string.rockets_landing_legs,
            core.legs
        )
    )

    details.add(
        CardWithListItemCheckboxImpl(
            R.string.rockets_gridfins,
            core.gridfins
        )
    )

    return details
}