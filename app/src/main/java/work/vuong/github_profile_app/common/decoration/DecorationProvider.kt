package work.vuong.github_profile_app.common.decoration

import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView

interface DecorationProvider {
    fun provide(res: Resources): List<RecyclerView.ItemDecoration>
}