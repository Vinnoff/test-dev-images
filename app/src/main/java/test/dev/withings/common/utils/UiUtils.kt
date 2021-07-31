package test.dev.withings.common.utils

import android.app.Activity
import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

infix fun ImageView.load(imageUrl: String?) = Glide
    .with(context)
    .load(imageUrl)
    .fitCenter()
    .transition(DrawableTransitionOptions.withCrossFade())
    .into(this)

fun Int.toPx(context: Context): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        context.resources.displayMetrics
    ).toInt()
}

fun Activity.hideKeyboard() = (currentFocus ?: View(this)).hideKeyboard()
fun Fragment.hideKeyboard() = view?.hideKeyboard()
fun View.hideKeyboard() {
    (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(windowToken, 0)
}