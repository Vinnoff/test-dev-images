package test.dev.withings.data.repo

import test.dev.withings.data.remote.response.ImagesResponse
import test.dev.withings.data.remote.source.RemoteDataSource

class ImagesRepositoryImpl(private val remoteDataSource: RemoteDataSource) : ImagesRepository {
    override suspend fun getImages(queries: List<String>): ImagesResponse? {
        return remoteDataSource.getImages(queries.joinToString(separator = "+"))
    }
}