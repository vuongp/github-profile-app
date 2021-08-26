package work.vuong.view_components.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import work.vuong.view_components.R
import work.vuong.view_components.common.diffutil.EqualsDiffUtil
import work.vuong.view_components.databinding.RepositoryItemBinding

class RepositoryItemAdapter :
    ListAdapter<RepositoryItemAdapter.ViewItem, RepositoryItemAdapter.ViewHolder>(EqualsDiffUtil<ViewItem>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RepositoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            loginName.text = item.loginName
            repositoryName.text = item.repositoryName
            repositoryDescription.text = item.repositoryDescription
            starCount.text = item.starCount.toString()
            primaryLanguage.text = item.primaryLanguage
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.repository_item
    }

    inner class ViewHolder(val binding: RepositoryItemBinding) : RecyclerView.ViewHolder(binding.root)

    data class ViewItem(
        val loginName: String,
        val repositoryName: String,
        val repositoryDescription: String,
        val starCount: Int,
        val primaryLanguage: String
    )

}