package com.vladislav.shumilov.launch_domain.model.local

interface Links {
    var id: String
    var launchId: String
    var missionPatch: String?
    var missionPatchSmall: String?
    var redditCampaign: String?
    var redditLaunch: String?
    var redditRecovery: String?
    var redditMedia: String?
    var presskit: String?
    var articleLink: String?
    var wikipedia: String?
    var videoLink: String?
    var youtubeId: String?
    var flickrImages: List<String>?
}