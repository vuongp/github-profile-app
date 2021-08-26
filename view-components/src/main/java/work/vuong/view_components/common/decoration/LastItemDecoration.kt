package work.vuong.view_components.common.decoration

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import android.view.View

class LastItemDecoration(
    private val left: Int? = null,
    private val top: Int? = null,
    private val right: Int? = null,
    private val bottom: Int? = null
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.getChildAdapterPosition(view) != parent.adapter?.itemCount?.minus(1)) return

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