package com.vladislav.shumilov.launch_data.repository

import com.vladislav.shumilov.core_data.util.generateRandomId
import com.vladislav.shumilov.launch_data.model.local.LinksImpl
import com.vladislav.shumilov.launch_data.model.remote.LinksResponseImpl
import com.vladislav.shumilov.launch_domain.repository.LinksRemoteRepository

class LinksRemoteRepositoryImpl:
    LinksRemoteRepository<LinksResponseImpl, LinksImpl> {

    override fun responseToModel(
        linksResponse: LinksResponseImpl,
        launchId: String
    ) =
        LinksImpl(
            generateId(),
            launchId,
            linksResponse.mission_patch,
            linksResponse.mission_patch_small,
            linksResponse.reddit_campaign,
            linksResponse.reddit_launch,
            linksResponse.reddit_recovery,
            linksResponse.reddit_media,
            linksResponse.presskit,
            linksResponse.article_link,
            linksResponse.wikipedia,
            linksResponse.video_link,
            linksResponse.youtube_id,
            linksResponse.flickr_images
        )

    override fun generateId() = generateRandomId()
}