package test.dev.withings.presentation.images

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import test.dev.withings.common.utils.CustomCoroutineExceptionHandler
import test.dev.withings.domain.entity.image.ImageData
import test.dev.withings.domain.interactors.GetImageListUseCase
import test.dev.withings.presentation.BaseViewModel
import test.dev.withings.presentation.Event
import test.dev.withings.presentation.images.search.GetImagesViewState
import test.dev.withings.presentation.toEvent

class ImagesViewModel(
    private val getImageListUseCase: GetImageListUseCase,
    dispatcher: CoroutineDispatcher
) : BaseViewModel(dispatcher) {
    private val _liveDataNavigation: MutableLiveData<Event<ImagesNavigation>> = MutableLiveData()
    val liveDataNavigation: LiveData<Event<ImagesNavigation>> get() = _liveDataNavigation
    private val _liveDataImageList: MutableLiveData<GetImagesViewState> = MutableLiveData()
    val liveDataImageList: LiveData<GetImagesViewState> get() = _liveDataImageList
    private val _liveDataImagesSelected: MutableLiveData<List<ImageData>> = MutableLiveData()
    val liveDataImagesSelected: LiveData<List<ImageData>> get() = _liveDataImagesSelected

    val idList: ArrayList<Int> = arrayListOf()

    init {
        _liveDataNavigation.value = ImagesNavigation.SEARCH.toEvent()
        _liveDataImageList.value = GetImagesViewState.LOADER
        launch(CustomCoroutineExceptionHandler { _liveDataImageList.value = GetImagesViewState.ERROR }) {
            _liveDataImageList.value = getImageListUseCase.invoke(emptyList())
        }
    }

    fun onImageClickedClicked(id: Int) {
        if (!idList.remove(id)) idList.add(id)
    }

    fun onConfirmSelection() {
        _liveDataNavigation.value = ImagesNavigation.SELECTION.toEvent()
        _liveDataImagesSelected.value = (_liveDataImageList.value as? GetImagesViewState.SUCCESS)?.data?.filter { idList.contains(it.id) }
    }
}