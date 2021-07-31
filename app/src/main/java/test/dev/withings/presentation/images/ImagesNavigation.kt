package test.dev.withings.presentation.images

sealed class ImagesNavigation {
    object SEARCH : ImagesNavigation()
    object SELECTION : ImagesNavigation()
}
