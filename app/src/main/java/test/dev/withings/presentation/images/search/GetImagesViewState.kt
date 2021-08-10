package test.dev.withings.presentation.images.search

import test.dev.withings.domain.entity.image.GetImageEntity
import test.dev.withings.domain.entity.image.ImageData

sealed class GetImagesViewState {
    object LOADER : GetImagesViewState()
    object ERROR : GetImagesViewState()
    object EMPTY : GetImagesViewState()
    data class SUCCESS(val data: MutableList<ImageDataView>) : GetImagesViewState()
}

class ImageDataView(
    val data: ImageData, var selected: Boolean = false
) {
    override fun equals(other: Any?) = data == (other as? ImageDataView)?.data
            && selected == (other as? ImageDataView)?.selected

    override fun toString(): String {
        return "$data | $selected"
    }
}

fun GetImageEntity.toViewState(): GetImagesViewState {
    return when (this) {
        is GetImageEntity.EMPTY -> GetImagesViewState.EMPTY
        is GetImageEntity.ERROR -> GetImagesViewState.ERROR
        is GetImageEntity.SUCCESS -> GetImagesViewState.SUCCESS(data.toViewState())
    }
}

private fun List<ImageData>.toViewState() = map { ImageDataView(it, false) }.toMutableList()
