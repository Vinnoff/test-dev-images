package test.dev.withings.data.repo

import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import test.dev.withings.data.remote.response.ImagesResponse
import test.dev.withings.data.remote.source.RemoteDataSource
import kotlin.test.assertEquals

class ImagesRepositoryImplTest {
    private val datasource: RemoteDataSource = mockk()

    private val classUnderTest = ImagesRepositoryImpl(datasource)

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `pixabay returns a result`() {
        runBlocking {
            //GIVEN
            val response = spyk(ImagesResponse(ArgumentMatchers.anyList()))
            coEvery { datasource.getImages(ArgumentMatchers.anyString()) } returns response

            //WHEN
            val result = classUnderTest.getImages(ArgumentMatchers.anyString())

            //THEN
            assertEquals(response, result)
            coVerify(exactly = 1) {
                datasource.getImages(ArgumentMatchers.anyString())
            }
            confirmVerified(datasource)
        }
    }

    @Test
    fun `pixabay returns null`() {
        runBlocking {
            //GIVEN
            val response = null
            coEvery { datasource.getImages(ArgumentMatchers.anyString()) } returns response

            //WHEN
            val result = classUnderTest.getImages(ArgumentMatchers.anyString())

            //THEN
            assertEquals(response, result)
            coVerify(exactly = 1) {
                datasource.getImages(ArgumentMatchers.anyString())
            }
            confirmVerified(datasource)
        }
    }
}