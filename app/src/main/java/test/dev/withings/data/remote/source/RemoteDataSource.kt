package test.dev.withings.data.remote.source

import test.dev.withings.data.remote.PixabayService
import test.dev.withings.data.remote.response.ImagesResponse

class RemoteDataSource(private val api: PixabayService) {
    suspend fun getImages(query: String): ImagesResponse? {
        val response = api.getImages(query)
        return when {
            response.isSuccessful -> response.body()
            else -> null
        }
    }
}