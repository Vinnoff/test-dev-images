package test.dev.withings.data.remote.response

data class ImagesResponse(
    val hits: List<HitResponse>?
)

data class HitResponse(
    val id: Int?,
    val pageURL: String?,
    val type: String?,
    val tags: String?,
    val previewURL: String?,
    val previewWidth: Int?,
    val previewHeight: Int?,
    val webformatURL: String?,
    val webformatWidth: Int?,
    val webformatHeight: Int?,
    val largeImageURL: String?,
    val imageWidth: Int?,
    val imageHeight: Int?,
    val imageSize: Int?,
    val views: Int?,
    val downloads: Int?,
    val collections: Int?,
    val likes: Int?,
    val comments: Int?,
    val user_id: Int?,
    val user: String?,
    val userImageURL: String?
)