package com.vladislav.shumilov.launch_ui.util

import android.content.res.Resources
import com.vladislav.shumilov.common_data.model.card_view_with_list.*
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemModel
import com.vladislav.shumilov.core_data.util.strFirstKeyToUpper
import com.vladislav.shumilov.core_data.util.unixTimeToHumanDateTime
import com.vladislav.shumilov.launch_domain.model.local.LaunchForDetail
import com.vladislav.shumilov.launch_ui.R
import com.vladislav.shumilov.rocket_ui.util.getCoreCardViewItems

fun getRocketDetailCardViewItemsForLaunch(
    launchForDetail: LaunchForDetail,
    resources: Resources
): List<CardWithListItemModel> {
    val details = mutableListOf<CardWithListItemModel>()

    val rocket = launchForDetail.rocketForDetail?.rocket

    rocket ?: return details

    details.add(CardWithListItemLinkImpl(R.string.rockets_rocket_model, rocket.name ?: ""))

    launchForDetail.launch.staticFireDateUnix?.let {
        details.add(
            CardWithListItemTextImpl(
                R.string.launches_static_fire,
                unixTimeToHumanDateTime(it)
            )
        )
    }

    launchForDetail.launch.launchWindow?.let {
        details.add(
            CardWithListItemTextImpl(
                R.string.launches_launch_window,
                formatLaunchWindow(it, resources)
            )
        )
    }

    details.add(
        CardWithListItemCheckboxImpl(
            R.string.launches_launch_success,
            launchForDetail.launch.launchSuccess
        )
    )

    if (!launchForDetail.launch.launchSuccess && launchForDetail.launchFailureDetails != null) {
        details.add(CardWithListItemDividerImpl())

        launchForDetail.launchFailureDetails?.time?.let {
            details.add(
                CardWithListItemTextImpl(
                    R.string.launches_failure_time,
                    formatFailureTime(it, resources)
                )
            )
        }

        launchForDetail.launchFailureDetails?.altitude?.let {
            details.add(CardWithListItemTextImpl(R.string.launches_failure_altitude, it))
        }

        launchForDetail.launchFailureDetails?.reason?.let {
            details.add(CardWithListItemDescriptionImpl(strFirstKeyToUpper(it)))
        }
    }

    launchForDetail.rocketForDetail?.firstStage?.cores?.first()?.let {
        details.add(CardWithListItemDividerImpl())
        details.addAll(getCoreCardViewItems(it))
    }

    return details
}


private fun formatLaunchWindow(launchWindow: Int, resources: Resources) =
    if (launchWindow == 0)
        resources.getString(com.example.launch_data.R.string.launches_launch_instantaneous)
    else launchWindow.toString()

private fun formatFailureTime(failureTime: Int, resources: Resources) =
    resources.getString(R.string.launches_failure_time_template, failureTime)
