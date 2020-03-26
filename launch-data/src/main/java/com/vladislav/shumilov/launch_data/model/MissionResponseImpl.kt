package com.vladislav.shumilov.launch_data.model

import com.vladislav.shumilov.launch_domain.model.MissionResponse

class MissionResponseImpl(override val name: String?, override val flight: String?) :
    MissionResponse