package test.dev.withings.presentation.images.search

import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.images_search_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import test.dev.withings.R
import test.dev.withings.common.utils.hideKeyboard
import test.dev.withings.common.utils.toPx
import test.dev.withings.presentation.BaseFragment
import test.dev.withings.presentation.SpaceItemDecoration
import test.dev.withings.presentation.images.ImagesViewModel

class ImagesSearchFragment : BaseFragment(R.layout.images_search_fragment) {
    private val imagesViewModel: ImagesViewModel by sharedViewModel()
    private val imagesAdapter by lazy { ImagesAdapter { id -> imagesViewModel.onImageClickedClicked(id) } }

    override fun initUI() {
        hideKeyboard()
        images_search_input.doOnTextChanged { input, _, _, _ -> imagesViewModel.onSearch(input.toString()) }
        images_search_input.setOnFocusChangeListener { v, hasFocus -> if (!hasFocus) hideKeyboard() }
        images_search_list.adapter = imagesAdapter
        images_search_list.addItemDecoration(SpaceItemDecoration(spacing = 20.toPx(requireContext())))
        images_search_confirm.setOnClickListener {
            imagesViewModel.onConfirmSelection()
            hideKeyboard()
        }
    }

    override fun initObserver() {
        imagesViewModel.liveDataImageList.observe(this) { event ->
            event.getContentIfNotHandled()?.let { viewState ->
                hideError()
                showLoader(viewState is GetImagesViewState.LOADER)
                when (viewState) {
                    is GetImagesViewState.EMPTY -> showError(getString(R.string.no_data_found))
                    is GetImagesViewState.ERROR -> showError(getString(R.string.error_text))
                    is GetImagesViewState.SUCCESS -> handleData(viewState.data)
                }
            }
        }
    }

    private fun handleData(data: MutableList<ImageDataView>) {
        images_search_list.isVisible = true
        imagesAdapter.submitList(data)
    }

    override fun showError(errorText: String?) {
        images_search_list.isInvisible = true
        images_search_error.text = errorText
        images_search_error.isVisible = true
    }

    fun hideError() {
        images_search_error.isVisible = false
    }
}