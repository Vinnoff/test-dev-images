package test.dev.withings.presentation.images

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import test.dev.withings.TestImages
import test.dev.withings.domain.entity.image.GetImageEntity
import test.dev.withings.domain.entity.image.ImageData
import test.dev.withings.domain.interactors.GetImageListUseCase
import test.dev.withings.presentation.Event
import test.dev.withings.presentation.images.search.GetImagesViewState

class ImagesViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getImageListUseCase: GetImageListUseCase = mockk()
    private val classUnderTest: ImagesViewModel by lazy { ImagesViewModel(getImageListUseCase, dispatcher) }

    @ExperimentalCoroutinesApi
    private val dispatcher = TestCoroutineDispatcher()

    private val navigationObserver: Observer<Event<ImagesNavigation>> = spyk()
    private val imageListObserver: Observer<Event<GetImagesViewState>> = spyk()
    private val imagesSelectedObserver: Observer<List<ImageData>> = spyk()
    private val imageInfoObserver: Observer<String> = spyk()

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `1, init ok`() {
        runBlocking {
            //GIVEN
            coEvery { getImageListUseCase.invoke("") } returns GetImageEntity.SUCCESS(TestImages.ENTITY_SUCCESS)

            //WHEN
            classUnderTest.liveDataNavigation.observeForever(navigationObserver)
            classUnderTest.liveDataImageList.observeForever(imageListObserver)

            //THEN
            coVerifySequence {
                getImageListUseCase.invoke("")
                navigationObserver.onChanged(Event(ImagesNavigation.SEARCH))
                imageListObserver.onChanged(Event(GetImagesViewState.SUCCESS(TestImages.SEARCH_VIEWSTATE_SUCCESS)))
            }
        }
    }

    @Test
    fun `1, init ko`() {
        runBlocking {
            //GIVEN
            coEvery { getImageListUseCase.invoke("") } returns GetImageEntity.ERROR

            //WHEN
            classUnderTest.liveDataNavigation.observeForever(navigationObserver)
            classUnderTest.liveDataImageList.observeForever(imageListObserver)

            //THEN
            coVerifySequence {
                getImageListUseCase.invoke("")
                navigationObserver.onChanged(Event(ImagesNavigation.SEARCH))
                imageListObserver.onChanged(Event(GetImagesViewState.ERROR))
            }
        }
    }

    @Test
    fun `1, init empty`() {
        runBlocking {
            //GIVEN
            coEvery { getImageListUseCase.invoke("") } returns GetImageEntity.EMPTY

            //WHEN
            classUnderTest.liveDataNavigation.observeForever(navigationObserver)
            classUnderTest.liveDataImageList.observeForever(imageListObserver)

            //THEN
            coVerifySequence {
                getImageListUseCase.invoke("")
                navigationObserver.onChanged(Event(ImagesNavigation.SEARCH))
                imageListObserver.onChanged(Event(GetImagesViewState.EMPTY))
            }
        }
    }

    @Test
    fun `1, init throw exception`() {
        runBlocking {
            //GIVEN
            coEvery { getImageListUseCase.invoke("") } throws Exception()

            //WHEN
            classUnderTest.liveDataNavigation.observeForever(navigationObserver)
            classUnderTest.liveDataImageList.observeForever(imageListObserver)

            //THEN
            coVerifySequence {
                getImageListUseCase.invoke("")
                navigationObserver.onChanged(Event(ImagesNavigation.SEARCH))
                imageListObserver.onChanged(Event(GetImagesViewState.ERROR))
            }
        }
    }

    @Test
    fun `2, change selection`() {
        runBlocking {
            //GIVEN
            coEvery { getImageListUseCase.invoke("") } returns GetImageEntity.SUCCESS(TestImages.ENTITY_SUCCESS)

            //WHEN
            classUnderTest.liveDataNavigation.observeForever(navigationObserver)
            classUnderTest.liveDataImageList.observeForever(imageListObserver)
            classUnderTest.liveDataImagesSelected.observeForever(imagesSelectedObserver)
            classUnderTest.onImageClicked(id = 0)
            classUnderTest.onImageClicked(id = 1)

            //THEN
            coVerifySequence {
                getImageListUseCase.invoke("")
                navigationObserver.onChanged(Event(ImagesNavigation.SEARCH))
                imageListObserver.onChanged(Event(GetImagesViewState.SUCCESS(TestImages.SEARCH_VIEWSTATE_SUCCESS)))
                imageListObserver.onChanged(Event(GetImagesViewState.SUCCESS(TestImages.SEARCH_VIEWSTATE_UPDATE_1)))
                imageListObserver.onChanged(Event(GetImagesViewState.SUCCESS(TestImages.SEARCH_VIEWSTATE_UPDATE_2)))
            }
        }
    }

    @Test
    fun `3, confirm selection`() {
        runBlocking {
            //GIVEN
            coEvery { getImageListUseCase.invoke("") } returns GetImageEntity.SUCCESS(TestImages.ENTITY_SUCCESS)

            //WHEN
            classUnderTest.liveDataNavigation.observeForever(navigationObserver)
            classUnderTest.liveDataImageList.observeForever(imageListObserver)
            classUnderTest.liveDataImagesSelected.observeForever(imagesSelectedObserver)
            classUnderTest.liveDataImageInfo.observeForever(imageInfoObserver)
            classUnderTest.onImageClicked(id = 0)
            classUnderTest.onImageClicked(id = 1)
            classUnderTest.onConfirmSelection()
            classUnderTest.onImageSelected(0)


            //THEN
            coVerifySequence {
                getImageListUseCase.invoke("")
                navigationObserver.onChanged(Event(ImagesNavigation.SEARCH))
                imageListObserver.onChanged(Event(GetImagesViewState.SUCCESS(TestImages.SEARCH_VIEWSTATE_SUCCESS)))
                imageListObserver.onChanged(Event(GetImagesViewState.SUCCESS(TestImages.SEARCH_VIEWSTATE_UPDATE_1)))
                imageListObserver.onChanged(Event(GetImagesViewState.SUCCESS(TestImages.SEARCH_VIEWSTATE_UPDATE_2)))
                navigationObserver.onChanged(Event(ImagesNavigation.SELECTION))
                imagesSelectedObserver.onChanged(TestImages.SELECTION_VIEWSTATE)
                imageInfoObserver.onChanged("Jodee Menges")
            }
        }
    }


    @After
    fun finally() {
        classUnderTest.liveDataNavigation.removeObserver(navigationObserver)
        classUnderTest.liveDataImageList.removeObserver(imageListObserver)
        classUnderTest.liveDataImagesSelected.removeObserver(imagesSelectedObserver)
        classUnderTest.liveDataImageInfo.removeObserver(imageInfoObserver)
    }
}