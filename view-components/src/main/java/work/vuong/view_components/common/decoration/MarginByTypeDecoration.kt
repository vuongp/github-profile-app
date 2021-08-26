package work.vuong.view_components.common.decoration

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import android.view.View

class MarginByTypeDecoration(
    private vararg val viewType: Int,
    private val left: Int? = null,
    private val top: Int? = null,
    private val right: Int? = null,
    private val bottom: Int? = null
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val itemViewType = parent.adapter?.getItemViewType(parent.getChildAdapterPosition(view)) ?: return
        if (!viewType.contains(itemViewType)) return

        left?.let {
            outRect.left = it
        }
        top?.let {
            outRect.top = it
        }
        right?.let {
            outRect.right = it
        }
        bottom?.let {
            outRect.bottom = it
        }
    }
}