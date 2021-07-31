package test.dev.withings.presentation.images

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import test.dev.withings.common.utils.CustomCoroutineExceptionHandler
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

    init {
        _liveDataNavigation.value = ImagesNavigation.SEARCH.toEvent()
        _liveDataImageList.value = GetImagesViewState.LOADER
        launch(CustomCoroutineExceptionHandler { _liveDataImageList.value = GetImagesViewState.ERROR }) {
            _liveDataImageList.value = getImageListUseCase.invoke(emptyList())
        }
    }

    fun onImageClickedClicked(id: Int) {

    }
}