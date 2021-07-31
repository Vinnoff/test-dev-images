package test.dev.withings.domain.interactors

import test.dev.withings.data.remote.response.HitResponse
import test.dev.withings.data.repo.ImagesRepository
import test.dev.withings.domain.entity.image.GetImageEntity
import test.dev.withings.domain.entity.image.ImageData

class GetImageListUseCase(private val imagesRepository: ImagesRepository) {
    suspend fun invoke(input: String): GetImageEntity {
        val entities = imagesRepository.getImages(input.replace(" ", "+"))?.hits?.mapNotNull { it.toEntity() }
        return when {
            entities == null -> GetImageEntity.ERROR
            entities.isEmpty() -> GetImageEntity.EMPTY
            else -> GetImageEntity.SUCCESS(entities)
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
