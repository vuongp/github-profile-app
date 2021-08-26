package work.vuong.view_components.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import work.vuong.view_components.R
import work.vuong.view_components.common.diffutil.EqualsDiffUtil
import work.vuong.view_components.databinding.HorizontalRepositoryListBinding

class HorizontalRepositoryListAdapter(
    val itemDecorations: List<RecyclerView.ItemDecoration> = emptyList()
) : ListAdapter<HorizontalRepositoryListAdapter.ViewItem, HorizontalRepositoryListAdapter.ViewHolder>(
    EqualsDiffUtil<ViewItem>()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            HorizontalRepositoryListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.adapter.submitList(item.repositoryItems)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.horizontal_repository_list
    }

    inner class ViewHolder(
        binding: HorizontalRepositoryListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        val adapter = RepositoryItemAdapter()

        init {
            binding.recyclerView.layoutManager = LinearLayoutManager(
                binding.root.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            itemDecorations.forEach {
                binding.recyclerView.addItemDecoration(it)
            }
            binding.recyclerView.adapter = adapter
        }

    }

    data class ViewItem(
        val repositoryItems: List<RepositoryItemAdapter.ViewItem>
    )

}