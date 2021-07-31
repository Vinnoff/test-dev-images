package test.dev.withings.presentation.images.search

import test.dev.withings.domain.entity.image.ImageData

sealed class GetImagesViewState {
    object LOADER : GetImagesViewState()
    object ERROR : GetImagesViewState()
    object EMPTY : GetImagesViewState()
    data class SUCCESS(val data: List<ImageData>) : GetImagesViewState()
}