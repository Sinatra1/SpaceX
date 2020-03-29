package com.vladislav.shumilov.launch_data.model.remote

import com.vladislav.shumilov.launch_domain.model.remote.MissionResponse

class MissionResponseImpl(override val name: String?, override val flight: String?) :
    MissionResponse