package tap.tap.youtubeviewer.viewmodel

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import tap.tap.youtubeviewer.database.getDatabase
import tap.tap.youtubeviewer.repository.VideoRepository
import java.io.IOException


class VideoViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * The data source this ViewModel will fetch results from.
     */
    private val videoRepository = VideoRepository(getDatabase(application))

    /**
     * A playlist of videos displayed on the screen.
     */
    val playlist = videoRepository.videos

    /**
     * Event triggered for tap.tap.youtubeviewer.network error. This is private to avoid exposing a
     * way to set this value to observers.
     */
    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    /**
     * Event triggered for tap.tap.youtubeviewer.network error. Views should use this to get access
     * to the data.
     */
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    /**
     * Flag to display the error message. This is private to avoid exposing a
     * way to set this value to observers.
     */
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    /**
     * Flag to display the error message. Views should use this to get access
     * to the data.
     */
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
        refreshDataFromRepository()
    }

    /**
     * Refresh data from the tap.tap.youtubeviewer.repository. Use a coroutine launch to run in a
     * background thread.
     */
    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                videoRepository.refreshVideos()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
                if (playlist.value.isNullOrEmpty())
                    _eventNetworkError.value = true
            }
        }
    }


    /**
     * Resets the tap.tap.youtubeviewer.network error flag.
     */
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    /**
     * Factory for constructing DevByteViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(VideoViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return VideoViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}