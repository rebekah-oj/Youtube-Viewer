package tap.tap.youtubeviewer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tap.tap.youtubeviewer.database.VideosDatabase
import tap.tap.youtubeviewer.database.asDomainModel
import tap.tap.youtubeviewer.models.Video
import tap.tap.youtubeviewer.network.DevByteNetwork
import tap.tap.youtubeviewer.network.asDatabaseModel
import timber.log.Timber

class VideoRepository(private val database: VideosDatabase) {

    val videos: LiveData<List<Video>> = Transformations.map(database.videoDao.getVideos()) {
        it.asDomainModel()
    }

    /**
     * Refresh the videos stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the tap.tap.youtubeviewer.database insert tap.tap.youtubeviewer.database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     */
    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            Timber.d("refresh videos is called")
            val playlist = DevByteNetwork.video.getPlaylist()
            database.videoDao.insertAll(playlist.asDatabaseModel())
        }
    }
}
