package test.dev.withings.presentation.images

import org.koin.androidx.viewmodel.ext.android.viewModel
import test.dev.withings.R
import test.dev.withings.presentation.BaseActivity
import test.dev.withings.presentation.images.search.ImagesSearchFragment
import test.dev.withings.presentation.images.selection.ImagesSelectionFragment

class ImagesActivity : BaseActivity(R.layout.images_activity) {
    private val imagesViewModel: ImagesViewModel by viewModel()

    override fun initUI() = Unit

    override fun initObserver() {
        imagesViewModel.liveDataNavigation.observe(this, { event ->
            event.getContentIfNotHandled()?.let { nav ->
                when (nav) {
                    ImagesNavigation.SEARCH -> goToFragment(ImagesSearchFragment(), R.id.image_fragment_container, addToBackStack = false)
                    ImagesNavigation.SELECTION -> goToFragment(ImagesSelectionFragment(), R.id.image_fragment_container)
                }
            }
        })
    }
}