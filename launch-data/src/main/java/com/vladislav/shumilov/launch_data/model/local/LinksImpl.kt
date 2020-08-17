package com.vladislav.shumilov.launch_data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.vladislav.shumilov.launch_data.model.local.LinksImpl.Companion.TABLE_NAME
import com.vladislav.shumilov.launch_domain.model.local.Links

@Entity(
    tableName = TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = LaunchImpl::class,
            parentColumns = [LaunchImpl.Columns.ID],
            childColumns = [LinksImpl.Columns.LAUNCH_ID],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LinksImpl(
    @PrimaryKey
    @ColumnInfo(name = Columns.ID)
    override var id: String,
    @ColumnInfo(name = Columns.LAUNCH_ID)
    override var launchId: String,
    @ColumnInfo(name = Columns.MISSION_PATCH)
    override var missionPatch: String?,
    @ColumnInfo(name = Columns.MISSION_PATCH_SMALL)
    override var missionPatchSmall: String?,
    @ColumnInfo(name = Columns.REDDIT_CAMPAIGN)
    override var redditCampaign: String?,
    @ColumnInfo(name = Columns.REDDIT_LAUNCH)
    override var redditLaunch: String?,
    @ColumnInfo(name = Columns.REDDIT_RECOVERY)
    override var redditRecovery: String?,
    @ColumnInfo(name = Columns.REDDIT_MEDIA)
    override var redditMedia: String?,
    @ColumnInfo(name = Columns.PRESSKIT)
    override var presskit: String?,
    @ColumnInfo(name = Columns.ARTICLE_LINK)
    override var articleLink: String?,
    @ColumnInfo(name = Columns.WIKIPEDIA)
    override var wikipedia: String?,
    @ColumnInfo(name = Columns.VIDEO_LINK)
    override var videoLink: String?,
    @ColumnInfo(name = Columns.YOUTUBE_ID)
    override var youtubeId: String?,
    @ColumnInfo(name = Columns.FLICKR_IMAGES)
    override var flickrImages: List<String>?
) : Links {

    companion object {
        const val TABLE_NAME = "links"
    }

    class Columns {
        companion object {
            const val ID = "id"
            const val LAUNCH_ID = "launch_id"
            const val MISSION_PATCH = "mission_patch"
            const val MISSION_PATCH_SMALL = "mission_patch_small"
            const val REDDIT_CAMPAIGN = "reddit_campaign"
            const val REDDIT_LAUNCH = "reddit_launch"
            const val REDDIT_RECOVERY = "reddit_recovery"
            const val REDDIT_MEDIA = "reddit_media"
            const val PRESSKIT = "presskit"
            const val ARTICLE_LINK = "article_link"
            const val WIKIPEDIA = "wikipedia"
            const val VIDEO_LINK = "video_link"
            const val YOUTUBE_ID = "youtubeId"
            const val FLICKR_IMAGES = "flickr_images"
        }
    }
}