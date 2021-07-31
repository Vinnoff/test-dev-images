package test.dev.withings.presentation.images.selection

import kotlinx.android.synthetic.main.images_selection_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import test.dev.withings.R
import test.dev.withings.common.utils.toPx
import test.dev.withings.presentation.BaseFragment
import test.dev.withings.presentation.SpaceItemDecoration
import test.dev.withings.presentation.images.ImagesViewModel

class ImagesSelectionFragment : BaseFragment(R.layout.images_selection_fragment) {
    private val imagesViewModel: ImagesViewModel by sharedViewModel()
    private val imagesAdapter by lazy { ImagesPresentationAdapter() }

    override fun initUI() {
        images_selection_list.adapter = imagesAdapter
        images_selection_list.addItemDecoration(SpaceItemDecoration(spacing = 20.toPx(requireContext())))
    }

    override fun initObserver() {
        imagesViewModel.liveDataImagesSelected.observe(this) {
            imagesAdapter.data = it
        }
    }
}