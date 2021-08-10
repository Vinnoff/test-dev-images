package test.dev.withings.domain.interactors

import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import test.dev.withings.TestImages
import test.dev.withings.data.remote.response.ImagesResponse
import test.dev.withings.data.repo.ImagesRepository
import test.dev.withings.domain.entity.image.GetImageEntity
import kotlin.test.assertEquals

class GetImageListUseCaseTest {
    private var imagesRepository: ImagesRepository = mockk()
    private var classUnderTest = GetImageListUseCase(imagesRepository)

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `All data`() {
        runBlocking {
            //GIVEN
            val expectedResult = GetImageEntity.SUCCESS(TestImages.ENTITY_SUCCESS)

            coEvery { imagesRepository.getImages(ArgumentMatchers.anyString()) } returns TestImages.RESPONSE_SUCCESS

            //WHEN
            val result = classUnderTest.invoke(ArgumentMatchers.anyString())

            //THEN
            assertEquals(expectedResult, result)
            coVerify(exactly = 1) {
                imagesRepository.getImages(ArgumentMatchers.anyString())
            }
        }
    }

    @Test
    fun `Data have a problem (null reference or empty string)`() {
        runBlocking {
            //GIVEN
            val expectedResult = GetImageEntity.EMPTY

            coEvery { imagesRepository.getImages(ArgumentMatchers.anyString()) } returns TestImages.RESPONSE_ERROR_FIELD

            //WHEN
            val result = classUnderTest.invoke(ArgumentMatchers.anyString())

            //THEN
            assertEquals(expectedResult, result)
            coVerify(exactly = 1) {
                imagesRepository.getImages(ArgumentMatchers.anyString())
            }
        }
    }

    @Test
    fun `Empty data`() {
        runBlocking {
            //GIVEN
            val expectedResult = GetImageEntity.EMPTY

            coEvery { imagesRepository.getImages(ArgumentMatchers.anyString()) } returns ImagesResponse(emptyList())

            //WHEN
            val result = classUnderTest.invoke(ArgumentMatchers.anyString())

            //THEN
            assertEquals(expectedResult, result)
            coVerify(exactly = 1) {
                imagesRepository.getImages(ArgumentMatchers.anyString())
            }
        }
    }

    @Test
    fun `Null data`() {
        runBlocking {
            //GIVEN
            val expectedResult = GetImageEntity.ERROR

            coEvery { imagesRepository.getImages(ArgumentMatchers.anyString()) } returns null

            //WHEN
            val result = classUnderTest.invoke(ArgumentMatchers.anyString())

            //THEN
            assertEquals(expectedResult, result)
            coVerify(exactly = 1) {
                imagesRepository.getImages(ArgumentMatchers.anyString())
            }
        }
    }
}