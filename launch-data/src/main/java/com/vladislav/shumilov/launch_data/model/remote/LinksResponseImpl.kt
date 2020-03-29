package com.vladislav.shumilov.launch_data.model.remote

import com.vladislav.shumilov.launch_domain.model.remote.LinksResponse

class LinksResponseImpl(
    override val mission_patch: String?,
    override val mission_patch_small: String?,
    override val reddit_campaign: String?,
    override val reddit_launch: String?,
    override val reddit_recovery: String?,
    override val reddit_media: String?,
    override val presskit: String?,
    override val article_link: String?,
    override val wikipedia: String?,
    override val video_link: String?,
    override val youtube_id: String?,
    override val flickr_images: List<String>?
) : LinksResponse