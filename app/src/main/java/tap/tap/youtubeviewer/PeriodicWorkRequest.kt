package tap.tap.youtubeviewer

import androidx.annotation.RequiresApi
import androidx.work.ListenableWorker
import androidx.work.PeriodicWorkRequest
import java.time.Duration
import java.util.concurrent.TimeUnit

/**
 * Creates a [PeriodicWorkRequest.Builder] with a given [ListenableWorker].
 *
 * @param repeatInterval @see [androidx.work.PeriodicWorkRequest.Builder]
 * @param repeatIntervalTimeUnit @see [androidx.work.PeriodicWorkRequest.Builder]
 */
inline fun <reified W : ListenableWorker> PeriodicWorkRequestBuilder(
    repeatInterval: Long,
    repeatIntervalTimeUnit: TimeUnit
): PeriodicWorkRequest.Builder {
    return PeriodicWorkRequest.Builder(W::class.java, repeatInterval, repeatIntervalTimeUnit)
}

/**
 * Creates a [PeriodicWorkRequest.Builder] with a given [ListenableWorker].
 *
 * @param repeatInterval @see [androidx.work.PeriodicWorkRequest.Builder]
 */
@RequiresApi(26)
inline fun <reified W : ListenableWorker> PeriodicWorkRequestBuilder(
    repeatInterval: Duration
): PeriodicWorkRequest.Builder {
    return PeriodicWorkRequest.Builder(W::class.java, repeatInterval)
}

/**
 * Creates a [PeriodicWorkRequest.Builder] with a given [ListenableWorker].
 *
 * @param repeatInterval @see [androidx.work.PeriodicWorkRequest.Builder]
 * @param repeatIntervalTimeUnit @see [androidx.work.PeriodicWorkRequest.Builder]
 * @param flexInterval @see [androidx.work.PeriodicWorkRequest.Builder]
 * @param flexIntervalTimeUnit @see [androidx.work.PeriodicWorkRequest.Builder]
 */
inline fun <reified W : ListenableWorker> PeriodicWorkRequestBuilder(
    repeatInterval: Long,
    repeatIntervalTimeUnit: TimeUnit,
    flexTimeInterval: Long,
    flexTimeIntervalUnit: TimeUnit
): PeriodicWorkRequest.Builder {

    return PeriodicWorkRequest.Builder(
        W::class.java,
        repeatInterval,
        repeatIntervalTimeUnit,
        flexTimeInterval,
        flexTimeIntervalUnit
    )
}

/**
 * Creates a [PeriodicWorkRequest.Builder] with a given [ListenableWorker].
 *
 * @param repeatInterval @see [androidx.work.PeriodicWorkRequest.Builder]
 * @param flexInterval @see [androidx.work.PeriodicWorkRequest.Builder]
 */
@RequiresApi(26)
inline fun <reified W : ListenableWorker> PeriodicWorkRequestBuilder(
    repeatInterval: Duration,
    flexTimeInterval: Duration
): PeriodicWorkRequest.Builder {
    return PeriodicWorkRequest.Builder(W::class.java, repeatInterval, flexTimeInterval)
}
