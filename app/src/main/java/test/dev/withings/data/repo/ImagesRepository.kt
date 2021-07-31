package test.dev.withings.data.repo

import test.dev.withings.data.remote.response.ImagesResponse

interface ImagesRepository {
    suspend fun getImages(queries: List<String>): ImagesResponse?
}
