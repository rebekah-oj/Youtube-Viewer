package tap.tap.youtubeviewer.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import retrofit2.HttpException
import tap.tap.youtubeviewer.database.getDatabase
import tap.tap.youtubeviewer.repository.VideoRepository
import timber.log.Timber


class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "com.example.android.devbyteviewer.work.RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = VideoRepository(database)

        try {
            repository.refreshVideos()
            Timber.d("WorkManager: Work request for sync is run")
        } catch (e: HttpException) {
            return Result.retry()
        }

        return Result.success()
    }
}