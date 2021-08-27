package work.vuong.view_components.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import work.vuong.view_components.R
import work.vuong.view_components.common.diffutil.EqualsDiffUtil
import work.vuong.view_components.databinding.TopBarTitleBinding

class TopBarTitleAdapter(
    vararg val viewItem: ViewItem
) : ListAdapter<TopBarTitleAdapter.ViewItem, TopBarTitleAdapter.ViewHolder>(
    EqualsDiffUtil<ViewItem>()
) {

    init {
        submitList(viewItem.toList())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TopBarTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.title.text = getItem(position).title
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.top_bar_title
    }

    class ViewHolder(val binding: TopBarTitleBinding): RecyclerView.ViewHolder(binding.root)

    data class ViewItem(val title: String)

}