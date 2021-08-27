package work.vuong.view_components.common.helper

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

fun <T, VH: RecyclerView.ViewHolder> ListAdapter<T, VH>.submitItem(item: T) {
    submitList(listOf(item))
}