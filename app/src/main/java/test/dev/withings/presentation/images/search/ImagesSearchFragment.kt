package test.dev.withings.presentation.images.search

import kotlinx.android.synthetic.main.images_search_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import test.dev.withings.R
import test.dev.withings.domain.entity.image.ImageData
import test.dev.withings.presentation.BaseFragment
import test.dev.withings.presentation.images.ImagesViewModel

class ImagesSearchFragment : BaseFragment(R.layout.images_search_fragment) {
    private val imagesViewModel: ImagesViewModel by sharedViewModel()
    private val imagesAdapter by lazy { ImagesAdapter { id -> imagesViewModel.onImageClickedClicked(id) } }

    override fun initUI() {
        images_search_list.adapter = imagesAdapter
    }

    override fun initObserver() {
        imagesViewModel.liveDataImageList.observe(this) { viewState ->
            showLoader(viewState is GetImagesViewState.LOADER)
            when (viewState) {
                is GetImagesViewState.EMPTY -> showError("No Data")
                is GetImagesViewState.ERROR -> showError()
                is GetImagesViewState.SUCCESS -> handleData(viewState.data)
            }
        }
    }

    private fun handleData(data: List<ImageData>) {
        imagesAdapter.data = data
    }
}