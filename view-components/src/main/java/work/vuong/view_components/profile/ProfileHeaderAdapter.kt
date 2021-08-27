package work.vuong.view_components.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import work.vuong.view_components.R
import work.vuong.view_components.common.diffutil.EqualsDiffUtil
import work.vuong.view_components.common.imageloader.ImageLoader
import work.vuong.view_components.databinding.ProfileHeaderBinding

class ProfileHeaderAdapter :
    ListAdapter<ProfileHeaderAdapter.ViewItem, ProfileHeaderAdapter.ViewHolder>(EqualsDiffUtil<ViewItem>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ProfileHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.fullName.text = item.fullName
        holder.binding.loginName.text = item.loginName
        holder.binding.email.text = item.email
        holder.binding.email.visibility = if (item.email.isNullOrEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
        holder.binding.followersCount.text = item.followersCount.toString()
        holder.binding.followingCount.text = item.followingCount.toString()
        item.profileImageLoader?.load(holder.binding.image)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.profile_header
    }

    inner class ViewHolder(val binding: ProfileHeaderBinding) : RecyclerView.ViewHolder(binding.root)

    data class ViewItem(
        val fullName: String,
        val loginName: String,
        val email: String?,
        val followersCount: Int,
        val followingCount: Int
    ){
        var profileImageLoader: ImageLoader? = null
    }

}