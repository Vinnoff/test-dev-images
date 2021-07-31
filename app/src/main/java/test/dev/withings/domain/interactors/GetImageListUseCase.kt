package test.dev.withings.domain.interactors

import test.dev.withings.data.remote.response.HitResponse
import test.dev.withings.data.repo.ImagesRepository

class GetImageListUseCase(private val imagesRepository: ImagesRepository) {
    suspend fun invoke(queries: List<String>): List<HitResponse> {
        return imagesRepository.getImages(queries)?.hits.orEmpty()
    }
}