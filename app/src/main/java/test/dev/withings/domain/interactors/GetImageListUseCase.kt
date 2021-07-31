package test.dev.withings.domain.interactors

import test.dev.withings.data.remote.response.HitResponse
import test.dev.withings.data.repo.ImagesRepository
import test.dev.withings.domain.entity.image.ImageData
import test.dev.withings.presentation.images.search.GetImagesViewState

class GetImageListUseCase(private val imagesRepository: ImagesRepository) {
    suspend fun invoke(queries: List<String>): GetImagesViewState {
        val entities = imagesRepository.getImages(queries)?.hits?.mapNotNull { it.toEntity() }
        return when {
            entities == null -> GetImagesViewState.ERROR
            entities.isEmpty() -> GetImagesViewState.EMPTY
            else -> GetImagesViewState.SUCCESS(entities)
        }
    }
}

fun HitResponse.toEntity() =
    if (id != null && user != null && previewURL != null && largeImageURL != null)
        ImageData(
            id = id,
            designer = user,
            previewUrl = previewURL,
            imageUrl = largeImageURL
        ) else null
