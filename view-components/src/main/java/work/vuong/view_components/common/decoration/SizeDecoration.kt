package work.vuong.view_components.common.decoration

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import android.view.View

class SizeDecoration(
    private val width: Int? = null,
    private val height: Int? = null
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        width?.let {
            view.layoutParams.width = width
        }

        height?.let {
            view.layoutParams.height = height
        }
    }
}