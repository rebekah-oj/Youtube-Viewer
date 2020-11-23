package tap.tap.youtubeviewer.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


interface ServiceBuilder {
    @GET("devbytes")
    suspend fun getPlaylist(): NetworkVideoContainer
}

/**
 * Main entry point for tap.tap.youtubeviewer.network access. Call like `DevByteNetwork.devbytes.getPlaylist()`
 */
object DevByteNetwork {

    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://android-kotlin-fun-mars-server.appspot.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val video = retrofit.create(ServiceBuilder::class.java)

}
