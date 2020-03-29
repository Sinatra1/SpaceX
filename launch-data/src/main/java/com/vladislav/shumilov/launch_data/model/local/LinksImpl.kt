package com.vladislav.shumilov.launch_data.model.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.vladislav.shumilov.launch_domain.model.local.Links

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = LaunchImpl::class,
            parentColumns = ["id"],
            childColumns = ["launch_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class LinksImpl(
    @PrimaryKey
    override var id: String,
    override var launch_id: String,
    override var mission_patch: String?,
    override var mission_patch_small: String?,
    override var reddit_campaign: String?,
    override var reddit_launch: String?,
    override var reddit_recovery: String?,
    override var reddit_media: String?,
    override var presskit: String?,
    override var article_link: String?,
    override var wikipedia: String?,
    override var video_link: String?,
    override var youtube_id: String?
    /*override var flickr_images: List<String>?*/
) : Links