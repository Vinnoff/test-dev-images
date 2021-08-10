package test.dev.withings.presentation.images

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import test.dev.withings.common.utils.CustomCoroutineExceptionHandler
import test.dev.withings.domain.entity.image.ImageData
import test.dev.withings.domain.interactors.GetImageListUseCase
import test.dev.withings.presentation.BaseViewModel
import test.dev.withings.presentation.Event
import test.dev.withings.presentation.images.search.GetImagesViewState
import test.dev.withings.presentation.images.search.toViewState
import test.dev.withings.presentation.toEvent

class ImagesViewModel(
    private val getImageListUseCase: GetImageListUseCase,
    dispatcher: CoroutineDispatcher
) : BaseViewModel(dispatcher) {
    private val _liveDataNavigation: MutableLiveData<Event<ImagesNavigation>> = MutableLiveData()
    val liveDataNavigation: LiveData<Event<ImagesNavigation>> get() = _liveDataNavigation
    private val _liveDataImageList: MutableLiveData<Event<GetImagesViewState>> = MutableLiveData()
    val liveDataImageList: LiveData<Event<GetImagesViewState>> get() = _liveDataImageList
    private val _liveDataImagesSelected: MutableLiveData<List<ImageData>> = MutableLiveData()
    val liveDataImagesSelected: LiveData<List<ImageData>> get() = _liveDataImagesSelected
    private val _liveDataImageInfo: MutableLiveData<String> = MutableLiveData()
    val liveDataImageInfo: LiveData<String> get() = _liveDataImageInfo

    private val imageList get() = (_liveDataImageList.value?.peekContent() as? GetImagesViewState.SUCCESS)?.data
    private var debounceJob: Job? = null

    init {
        _liveDataNavigation.value = ImagesNavigation.SEARCH.toEvent()
        getImages("")
    }

    fun onImageClicked(id: Int) {
        _liveDataImageList.value = imageList?.let { list ->
            list[list.indexOfFirst { id == it.data.id }].apply {
                selected = !selected
            }
            GetImagesViewState.SUCCESS(list).toEvent()
        }
    }

    fun onConfirmSelection() {
        _liveDataNavigation.value = ImagesNavigation.SELECTION.toEvent()
        _liveDataImagesSelected.value = imageList?.mapNotNull { if (it.selected) it.data else null }
    }

    fun onSearch(input: String) {
        debounce {
            getImages(input)
        }
    }

    private fun getImages(input: String) {
        _liveDataImageList.value = GetImagesViewState.LOADER.toEvent()
        launch(CustomCoroutineExceptionHandler { _liveDataImageList.value = GetImagesViewState.ERROR.toEvent() }) {
            _liveDataImageList.value = getImageListUseCase.invoke(input).toViewState().toEvent()
        }
    }

    fun onImageSelected(position: Int) {
        _liveDataImageInfo.value = liveDataImagesSelected.value?.getOrNull(position)?.designer
    }

    fun debounce(l: Long = 300L, function: suspend CoroutineScope.() -> Unit) {
        debounceJob?.cancel()
        debounceJob = launch {
            delay(l)
            function.invoke(this)
        }
    }
}