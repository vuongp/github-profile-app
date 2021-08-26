package work.vuong.github_profile_app.screen.profile

import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import work.vuong.github_profile_app.R
import work.vuong.github_profile_app.common.decoration.DecorationProvider
import work.vuong.view_components.common.decoration.LastItemDecoration
import work.vuong.view_components.common.decoration.MarginByTypeDecoration

class ProfileDecorationProvider : DecorationProvider {

    override fun provide(res: Resources): List<RecyclerView.ItemDecoration> {
        val materialPoint2 = res.getDimensionPixelSize(R.dimen.material_point_2)
        val materialPoint3 = res.getDimensionPixelSize(R.dimen.material_point_3)

        return listOf(
            MarginByTypeDecoration(
                R.layout.profile_header,
                R.layout.category_title,
                left = materialPoint2,
                right = materialPoint2,
                top = materialPoint3
            ),
            MarginByTypeDecoration(
                R.layout.repository_item,
                left = materialPoint2,
                right = materialPoint2,
                top = materialPoint2
            ),
            MarginByTypeDecoration(
                R.layout.horizontal_repository_list,
                top = materialPoint2
            ),
            LastItemDecoration(
                bottom = materialPoint2
            )
        )
    }

}