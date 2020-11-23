package tap.tap.youtubeviewer.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Database


@Dao
interface VideoDao {
    @Query("select * from `database`")
    fun getVideos(): LiveData<List<tap.tap.youtubeviewer.database.Database>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(videos: List<tap.tap.youtubeviewer.database.Database>)
}


@Database(entities = [tap.tap.youtubeviewer.database.Database::class], version = 1)
abstract class VideosDatabase : RoomDatabase() {
    abstract val videoDao: VideoDao
}

private lateinit var INSTANCE: VideosDatabase

fun getDatabase(context: Context): VideosDatabase {
    synchronized(VideosDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                VideosDatabase::class.java,
                "videos"
            ).build()
        }
    }
    return INSTANCE
}
