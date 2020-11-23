package tap.tap.youtubeviewer.network

import com.squareup.moshi.JsonClass
import tap.tap.youtubeviewer.database.Database
import tap.tap.youtubeviewer.models.Video

@JsonClass(generateAdapter = true)
data class NetworkVideoContainer(val videos: List<NetworkVideo>)

/**
 * Videos represent a devbyte that can be played.
 */
@JsonClass(generateAdapter = true)
data class NetworkVideo(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String,
    val closedCaptions: String?
)

/**
 * Convert Network results to tap.tap.youtubeviewer.database objects
 */
fun NetworkVideoContainer.asDomainModel(): List<Video> {
    return videos.map {
        Video(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail
        )
    }
}


/**
 * Convert Network results to tap.tap.youtubeviewer.database objects
 */
fun NetworkVideoContainer.asDatabaseModel(): List<Database> {
    return videos.map {
        Database(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail
        )
    }
}
