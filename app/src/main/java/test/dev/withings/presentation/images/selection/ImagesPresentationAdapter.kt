package test.dev.withings.presentation.images.selection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.images_item.view.*
import test.dev.withings.R
import test.dev.withings.common.utils.load
import test.dev.withings.domain.entity.image.ImageData

class ImagesPresentationAdapter() : RecyclerView.Adapter<ImagesPresentationAdapter.ViewHolder>() {
    private val _data = arrayListOf<ImageData>()
    var data: List<ImageData>
        get() = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = _data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.images_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        _data[position].let { data ->
            holder.bind(data)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(content: ImageData) = with(itemView) {
            result_image.load(content.previewUrl)
            result_name.text = content.designer
            result_check.isGone
        }
    }
}

