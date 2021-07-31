package test.dev.withings.presentation.images

import android.widget.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import test.dev.withings.R
import test.dev.withings.presentation.BaseActivity

class ImagesActivity : BaseActivity(R.layout.images_activity) {
    private val imagesViewModel: ImagesViewModel by viewModel()

    override fun initUI() = Unit

    override fun initObserver() {
        imagesViewModel.liveDataImageList.observe(this) {
            Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
        }
    }
}