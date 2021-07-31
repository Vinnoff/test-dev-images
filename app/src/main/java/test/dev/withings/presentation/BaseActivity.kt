package test.dev.withings.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import test.dev.withings.BuildConfig

abstract class BaseActivity(@LayoutRes val layoutRes: Int) : AppCompatActivity(), LifecycleOwner {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
    }

    override fun onStart() {
        super.onStart()
        initUI()
        initObserver()
    }

    abstract fun initUI()
    abstract fun initObserver()
    open fun showLoader(isLoading: Boolean) {
        if (BuildConfig.DEBUG) Toast.makeText(this, "Loading : $isLoading", Toast.LENGTH_SHORT).show()
    }

    open fun showError(errorText: String? = null) {
        if (BuildConfig.DEBUG) Toast.makeText(this, errorText ?: "Error", Toast.LENGTH_SHORT).show()
    }
}