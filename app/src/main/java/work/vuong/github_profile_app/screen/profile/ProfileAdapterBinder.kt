package work.vuong.github_profile_app.screen.profile

import android.content.res.Resources
import androidx.recyclerview.widget.ConcatAdapter
import work.vuong.github_profile_app.R
import work.vuong.github_profile_app.common.adapterbinder.AdapterBinder
import work.vuong.view_components.common.decoration.LastItemDecoration
import work.vuong.view_components.common.decoration.MarginByTypeDecoration
import work.vuong.view_components.common.decoration.SizeDecoration
import work.vuong.view_components.profile.CategoryTitleAdapter
import work.vuong.view_components.profile.HorizontalRepositoryListAdapter
import work.vuong.view_components.profile.ProfileHeaderAdapter
import work.vuong.view_components.profile.RepositoryItemAdapter
import work.vuong.view_components.profile.TopBarTitleAdapter
import javax.inject.Inject

class ProfileAdapterBinder @Inject constructor(
    private val resources: Resources
): AdapterBinder<ConcatAdapter, Any> {

    override fun bind(adapter: ConcatAdapter, item: Any) {
        adapter.addAdapter(TopBarTitleAdapter().apply {
            submitList(
                listOf(
                    TopBarTitleAdapter.ViewItem("Profile")
                )
            )
        })

        adapter.addAdapter(ProfileHeaderAdapter().apply {
            submitList(
                listOf(
                    ProfileHeaderAdapter.ViewItem(
                        "Sian Taylor",
                        "setaylor",
                        "s.e.taylor@gmail.com",
                        48,
                        72,
                    )
                )
            )
        })

        adapter.addAdapter(CategoryTitleAdapter().apply {
            submitList(
                listOf(
                    CategoryTitleAdapter.ViewItem(
                        "Pinned"
                    )
                )
            )
        })

        val repositoryItem = RepositoryItemAdapter.ViewItem(
            "setaylor",
            "telegraph-android",
            "Telegraph X is Android client",
            75,
            "Kotlin"
        )
        adapter.addAdapter(RepositoryItemAdapter().apply {
            submitList(
                (0..3).map {
                    repositoryItem
                }
            )
        })

        adapter.addAdapter(CategoryTitleAdapter().apply {
            submitList(
                listOf(
                    CategoryTitleAdapter.ViewItem(
                        "Top repositories"
                    )
                )
            )
        })

        val materialPoint2 = resources.getDimensionPixelSize(R.dimen.material_point_2)
        val smallRepositoryWidth = resources.getDimensionPixelSize(R.dimen.small_repository_width)
        val horizontalRepositoryListDecorations = listOf(
            MarginByTypeDecoration(
                R.layout.repository_item,
                left = materialPoint2
            ),
            LastItemDecoration(
                right = materialPoint2
            ),
            SizeDecoration(
                width = smallRepositoryWidth
            )
        )
        adapter.addAdapter(HorizontalRepositoryListAdapter(horizontalRepositoryListDecorations).apply {
            submitList(
                listOf(
                    HorizontalRepositoryListAdapter.ViewItem(
                        (0..10).map {
                            repositoryItem
                        }
                    )
                )
            )
        })

        adapter.addAdapter(CategoryTitleAdapter().apply {
            submitList(
                listOf(
                    CategoryTitleAdapter.ViewItem(
                        "Starred repositories"
                    )
                )
            )
        })

        adapter.addAdapter(HorizontalRepositoryListAdapter(horizontalRepositoryListDecorations).apply {
            submitList(
                listOf(
                    HorizontalRepositoryListAdapter.ViewItem(
                        (0..10).map {
                            repositoryItem
                        }
                    )
                )
            )
        })
    }

}