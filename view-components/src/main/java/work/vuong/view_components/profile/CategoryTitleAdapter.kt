package work.vuong.view_components.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import work.vuong.view_components.R
import work.vuong.view_components.common.diffutil.EqualsDiffUtil
import work.vuong.view_components.databinding.CategoryTitleBinding

class CategoryTitleAdapter : ListAdapter<CategoryTitleAdapter.ViewItem, CategoryTitleAdapter.ViewHolder>(
    EqualsDiffUtil<ViewItem>()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CategoryTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.title.text = getItem(position).title
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.category_title
    }

    inner class ViewHolder(val binding: CategoryTitleBinding): RecyclerView.ViewHolder(binding.root)

    data class ViewItem(val title: String)

}