package test.dev.withings.presentation.images.selection

import androidx.recyclerview.widget.LinearSnapHelper
import kotlinx.android.synthetic.main.images_selection_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import test.dev.withings.R
import test.dev.withings.common.utils.toPx
import test.dev.withings.presentation.*
import test.dev.withings.presentation.images.ImagesViewModel

class ImagesSelectionFragment : BaseFragment(R.layout.images_selection_fragment) {
    private val imagesViewModel: ImagesViewModel by sharedViewModel()
    private val imagesAdapter by lazy { ImagesPresentationAdapter() }

    override fun initUI() {
        images_selection_list.adapter = imagesAdapter
        images_selection_list.addItemDecoration(SpaceItemDecoration(spacing = 20.toPx(requireContext())))
        images_selection_list.attachSnapHelperWithListener(LinearSnapHelper(), SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE, object : OnSnapPositionChangeListener {
            override fun onSnapPositionChange(position: Int) {
                imagesViewModel.onImageSelected(position)
            }
        })
        imagesViewModel.onImageSelected(0)
    }

    override fun initObserver() {
        imagesViewModel.liveDataImagesSelected.observe(this) {
            imagesAdapter.data = it
        }

        imagesViewModel.liveDataImageInfo.observe(this) {
            images_selection_info.text = it
        }
    }
}