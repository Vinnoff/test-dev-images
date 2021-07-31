package test.dev.withings.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import test.dev.withings.data.remote.response.ImagesResponse

interface PixabayService {
    @GET("/api")
    suspend fun getImages(
        @Query("q") query: String,
    ): Response<ImagesResponse>
}