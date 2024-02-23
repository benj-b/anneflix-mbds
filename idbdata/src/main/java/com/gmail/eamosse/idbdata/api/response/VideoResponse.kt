package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Video
import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("results") val results: List<VideoItem>
) {
    data class VideoItem(
        @SerializedName("key") val key: String,
        @SerializedName("name") val name: String
    ) {
        fun toVideo(): Video {
            return Video(key, name)
        }
    }
}