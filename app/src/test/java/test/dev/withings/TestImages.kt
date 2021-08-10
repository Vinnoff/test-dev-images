package test.dev.withings

import test.dev.withings.data.remote.response.HitResponse
import test.dev.withings.data.remote.response.ImagesResponse
import test.dev.withings.domain.entity.image.ImageData
import test.dev.withings.presentation.images.search.ImageDataView

class TestImages {
    companion object {
        val RESPONSE_SUCCESS = ImagesResponse(
            hits = listOf(
                HitResponse(id = 0, previewURL = "previewUrl", largeImageURL = "imageUrl", user = "Jodee Menges", userImageURL = null, pageURL = null, type = null, tags = null, previewWidth = null, previewHeight = null, webformatURL = null, webformatWidth = null, webformatHeight = null, imageWidth = null, imageHeight = null, imageSize = null, views = null, downloads = null, collections = null, likes = null, comments = null, user_id = null),
                HitResponse(id = 1, previewURL = "previewUrl", largeImageURL = "imageUrl", user = "Leda Bianchi", userImageURL = null, pageURL = null, type = null, tags = null, previewWidth = null, previewHeight = null, webformatURL = null, webformatWidth = null, webformatHeight = null, imageWidth = null, imageHeight = null, imageSize = null, views = null, downloads = null, collections = null, likes = null, comments = null, user_id = null),
                HitResponse(id = 2, previewURL = "previewUrl", largeImageURL = "imageUrl", user = "Maile Planck", userImageURL = null, pageURL = null, type = null, tags = null, previewWidth = null, previewHeight = null, webformatURL = null, webformatWidth = null, webformatHeight = null, imageWidth = null, imageHeight = null, imageSize = null, views = null, downloads = null, collections = null, likes = null, comments = null, user_id = null),
                HitResponse(id = 3, previewURL = "previewUrl", largeImageURL = "imageUrl", user = "Vernell Loden", userImageURL = null, pageURL = null, type = null, tags = null, previewWidth = null, previewHeight = null, webformatURL = null, webformatWidth = null, webformatHeight = null, imageWidth = null, imageHeight = null, imageSize = null, views = null, downloads = null, collections = null, likes = null, comments = null, user_id = null),
                HitResponse(id = 4, previewURL = "previewUrl", largeImageURL = "imageUrl", user = "Elly Gloster", userImageURL = null, pageURL = null, type = null, tags = null, previewWidth = null, previewHeight = null, webformatURL = null, webformatWidth = null, webformatHeight = null, imageWidth = null, imageHeight = null, imageSize = null, views = null, downloads = null, collections = null, likes = null, comments = null, user_id = null),
                HitResponse(id = 5, previewURL = "previewUrl", largeImageURL = "imageUrl", user = "Holley Mckell", userImageURL = null, pageURL = null, type = null, tags = null, previewWidth = null, previewHeight = null, webformatURL = null, webformatWidth = null, webformatHeight = null, imageWidth = null, imageHeight = null, imageSize = null, views = null, downloads = null, collections = null, likes = null, comments = null, user_id = null),
                HitResponse(id = 6, previewURL = "previewUrl", largeImageURL = "imageUrl", user = "Byron Fontana", userImageURL = null, pageURL = null, type = null, tags = null, previewWidth = null, previewHeight = null, webformatURL = null, webformatWidth = null, webformatHeight = null, imageWidth = null, imageHeight = null, imageSize = null, views = null, downloads = null, collections = null, likes = null, comments = null, user_id = null),
            )
        )
        val RESPONSE_ERROR_FIELD = ImagesResponse(
            hits = listOf(
                HitResponse(id = null, previewURL = "previewUrl", largeImageURL = "imageUrl", user = "Jodee Menges", userImageURL = null, pageURL = null, type = null, tags = null, previewWidth = null, previewHeight = null, webformatURL = null, webformatWidth = null, webformatHeight = null, imageWidth = null, imageHeight = null, imageSize = null, views = null, downloads = null, collections = null, likes = null, comments = null, user_id = null),
                HitResponse(id = 1, previewURL = null, largeImageURL = "imageUrl", user = "Leda Bianchi", userImageURL = null, pageURL = null, type = null, tags = null, previewWidth = null, previewHeight = null, webformatURL = null, webformatWidth = null, webformatHeight = null, imageWidth = null, imageHeight = null, imageSize = null, views = null, downloads = null, collections = null, likes = null, comments = null, user_id = null),
                HitResponse(id = 2, previewURL = "previewUrl", largeImageURL = null, user = "Maile Planck", userImageURL = null, pageURL = null, type = null, tags = null, previewWidth = null, previewHeight = null, webformatURL = null, webformatWidth = null, webformatHeight = null, imageWidth = null, imageHeight = null, imageSize = null, views = null, downloads = null, collections = null, likes = null, comments = null, user_id = null),
                HitResponse(id = 3, previewURL = "previewUrl", largeImageURL = "imageUrl", user = null, userImageURL = null, pageURL = null, type = null, tags = null, previewWidth = null, previewHeight = null, webformatURL = null, webformatWidth = null, webformatHeight = null, imageWidth = null, imageHeight = null, imageSize = null, views = null, downloads = null, collections = null, likes = null, comments = null, user_id = null),
            )
        )

        val ENTITY_SUCCESS = listOf(
            ImageData(id = 0, designer = "Jodee Menges", previewUrl = "previewUrl", imageUrl = "imageUrl"),
            ImageData(id = 1, designer = "Leda Bianchi", previewUrl = "previewUrl", imageUrl = "imageUrl"),
            ImageData(id = 2, designer = "Maile Planck", previewUrl = "previewUrl", imageUrl = "imageUrl"),
            ImageData(id = 3, designer = "Vernell Loden", previewUrl = "previewUrl", imageUrl = "imageUrl"),
            ImageData(id = 4, designer = "Elly Gloster", previewUrl = "previewUrl", imageUrl = "imageUrl"),
            ImageData(id = 5, designer = "Holley Mckell", previewUrl = "previewUrl", imageUrl = "imageUrl"),
            ImageData(id = 6, designer = "Byron Fontana", previewUrl = "previewUrl", imageUrl = "imageUrl"),
        )

        val SEARCH_VIEWSTATE_SUCCESS = mutableListOf(
            ImageDataView(data = ImageData(id = 0, designer = "Jodee Menges", previewUrl = "previewUrl", imageUrl = "imageUrl"), false),
            ImageDataView(data = ImageData(id = 1, designer = "Leda Bianchi", previewUrl = "previewUrl", imageUrl = "imageUrl"), false),
            ImageDataView(data = ImageData(id = 2, designer = "Maile Planck", previewUrl = "previewUrl", imageUrl = "imageUrl"), false),
            ImageDataView(data = ImageData(id = 3, designer = "Vernell Loden", previewUrl = "previewUrl", imageUrl = "imageUrl"), false),
            ImageDataView(data = ImageData(id = 4, designer = "Elly Gloster", previewUrl = "previewUrl", imageUrl = "imageUrl"), false),
            ImageDataView(data = ImageData(id = 5, designer = "Holley Mckell", previewUrl = "previewUrl", imageUrl = "imageUrl"), false),
            ImageDataView(data = ImageData(id = 6, designer = "Byron Fontana", previewUrl = "previewUrl", imageUrl = "imageUrl"), false),
        )

        val SEARCH_VIEWSTATE_UPDATE_1 = mutableListOf(
            ImageDataView(data = ImageData(id = 0, designer = "Jodee Menges", previewUrl = "previewUrl", imageUrl = "imageUrl"), true),
            ImageDataView(data = ImageData(id = 1, designer = "Leda Bianchi", previewUrl = "previewUrl", imageUrl = "imageUrl"), false),
            ImageDataView(data = ImageData(id = 2, designer = "Maile Planck", previewUrl = "previewUrl", imageUrl = "imageUrl"), false),
            ImageDataView(data = ImageData(id = 3, designer = "Vernell Loden", previewUrl = "previewUrl", imageUrl = "imageUrl"), false),
            ImageDataView(data = ImageData(id = 4, designer = "Elly Gloster", previewUrl = "previewUrl", imageUrl = "imageUrl"), false),
            ImageDataView(data = ImageData(id = 5, designer = "Holley Mckell", previewUrl = "previewUrl", imageUrl = "imageUrl"), false),
            ImageDataView(data = ImageData(id = 6, designer = "Byron Fontana", previewUrl = "previewUrl", imageUrl = "imageUrl"), false),
        )
        val SEARCH_VIEWSTATE_UPDATE_2 = mutableListOf(
            ImageDataView(data = ImageData(id = 0, designer = "Jodee Menges", previewUrl = "previewUrl", imageUrl = "imageUrl"), true),
            ImageDataView(data = ImageData(id = 1, designer = "Leda Bianchi", previewUrl = "previewUrl", imageUrl = "imageUrl"), true),
            ImageDataView(data = ImageData(id = 2, designer = "Maile Planck", previewUrl = "previewUrl", imageUrl = "imageUrl"), false),
            ImageDataView(data = ImageData(id = 3, designer = "Vernell Loden", previewUrl = "previewUrl", imageUrl = "imageUrl"), false),
            ImageDataView(data = ImageData(id = 4, designer = "Elly Gloster", previewUrl = "previewUrl", imageUrl = "imageUrl"), false),
            ImageDataView(data = ImageData(id = 5, designer = "Holley Mckell", previewUrl = "previewUrl", imageUrl = "imageUrl"), false),
            ImageDataView(data = ImageData(id = 6, designer = "Byron Fontana", previewUrl = "previewUrl", imageUrl = "imageUrl"), false),
        )

        val SELECTION_VIEWSTATE = listOf(
            ImageData(id = 0, designer = "Jodee Menges", previewUrl = "previewUrl", imageUrl = "imageUrl"),
            ImageData(id = 1, designer = "Leda Bianchi", previewUrl = "previewUrl", imageUrl = "imageUrl"),
        )
    }
}
