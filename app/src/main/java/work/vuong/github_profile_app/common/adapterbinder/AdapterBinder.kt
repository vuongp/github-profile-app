package work.vuong.github_profile_app.common.adapterbinder

import androidx.recyclerview.widget.RecyclerView

interface AdapterBinder<A: RecyclerView.Adapter<RecyclerView.ViewHolder>, T> {
    fun bind(adapter: A, item: T)
}