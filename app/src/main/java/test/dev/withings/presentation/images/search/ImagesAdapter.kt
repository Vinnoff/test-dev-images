package test.dev.withings.presentation.images.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.images_item.view.*
import test.dev.withings.R
import test.dev.withings.common.utils.load
import test.dev.withings.domain.entity.image.ImageData

class ImagesAdapter(val onImage: (id: Int) -> Unit) : ListAdapter<ImageDataView, ImagesAdapter.ViewHolder>(ItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.images_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        currentList[position].let { data ->
            holder.bind(data.data, data.selected)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(content: ImageData, isSelected: Boolean) = with(itemView) {
            result_image.load(content.previewUrl)
            result_name.text = content.designer
            result_check.isChecked = isSelected
            result_check.setOnClickListener { onImage(content.id) }
        }
    }

    class ItemCallback : DiffUtil.ItemCallback<ImageDataView>() {
        override fun areItemsTheSame(oldItem: ImageDataView, newItem: ImageDataView) = oldItem.data.id == newItem.data.id
        override fun areContentsTheSame(oldItem: ImageDataView, newItem: ImageDataView) = oldItem.equals(newItem)
    }
}

