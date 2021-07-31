package test.dev.withings.domain.entity.image

sealed class GetImageEntity {
    object ERROR : GetImageEntity()
    object EMPTY : GetImageEntity()
    data class SUCCESS(val data: List<ImageData>) : GetImageEntity()
}
