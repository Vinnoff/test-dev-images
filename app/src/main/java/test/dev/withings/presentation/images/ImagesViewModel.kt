package test.dev.withings.presentation.images

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import test.dev.withings.data.remote.response.HitResponse
import test.dev.withings.domain.interactors.GetImageListUseCase
import test.dev.withings.presentation.BaseViewModel

class ImagesViewModel(
    private val getImageListUseCase: GetImageListUseCase,
    dispatcher: CoroutineDispatcher
) : BaseViewModel(dispatcher) {
    private val _liveDataImageList: MutableLiveData<List<HitResponse>> = MutableLiveData()
    val liveDataImageList: LiveData<List<HitResponse>> get() = _liveDataImageList

    init {
        launch {
            _liveDataImageList.value = getImageListUseCase.invoke(emptyList())
        }
    }
}