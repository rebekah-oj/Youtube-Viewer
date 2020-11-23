package tap.tap.youtubeviewer.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import tap.tap.youtubeviewer.models.Video

@Entity
data class Database constructor(
    @PrimaryKey
    val url: String,
    val updated: String,
    val title: String,
    val description: String,
    val thumbnail: String
)


/**
 * Map DatabaseVideos to domain entities
 */
fun List<Database>.asDomainModel(): List<Video> {
    return map {
        Video(
            url = it.url,
            title = it.title,
            description = it.description,
            updated = it.updated,
            thumbnail = it.thumbnail
        )
    }
}