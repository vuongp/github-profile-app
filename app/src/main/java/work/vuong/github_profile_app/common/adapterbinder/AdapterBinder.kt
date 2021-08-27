package work.vuong.github_profile_app.common.adapterbinder

import androidx.recyclerview.widget.RecyclerView

/**
 * Interface for binding data to adapters
 *  therefore filling adapters with the needed data to display view within a recyclerview
 */
interface AdapterBinder<A : RecyclerView.Adapter<*>, T> {
    fun bind(adapter: A)
    fun update(adapter: A, item: T)
}