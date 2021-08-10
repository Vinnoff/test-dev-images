package test.dev.withings.data.remote.source

import io.mockk.*
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import retrofit2.Response
import test.dev.withings.data.remote.PixabayService
import test.dev.withings.data.remote.response.ImagesResponse
import kotlin.test.assertEquals

class RemoteDataSourceTest {
    private val randomUserService: PixabayService = mockk()
    private val classUnderTest = RemoteDataSource(randomUserService)

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `pixabay api (success 200)`() {
        runBlocking {
            // GIVEN
            val response = spyk(ImagesResponse(ArgumentMatchers.anyList()))

            coEvery { randomUserService.getImages(ArgumentMatchers.anyString()) } returns Response.success(response)

            // WHEN
            val result = classUnderTest.getImages(ArgumentMatchers.anyString())

            //THEN
            assertEquals(response, result)
            coVerify(exactly = 1) {
                randomUserService.getImages(ArgumentMatchers.anyString())
            }
        }
    }

    @Test
    fun `pixabay api (error 500)`() {
        runBlocking {
            // GIVEN
            coEvery { randomUserService.getImages(ArgumentMatchers.anyString()) } returns Response.error(500, ResponseBody.create(MediaType.parse("application/json"), "{}"))

            // WHEN
            val result = classUnderTest.getImages(ArgumentMatchers.anyString())

            //THEN
            assertEquals(null, result)
            coVerify(exactly = 1) {
                randomUserService.getImages(ArgumentMatchers.anyString())
            }
        }
    }
}