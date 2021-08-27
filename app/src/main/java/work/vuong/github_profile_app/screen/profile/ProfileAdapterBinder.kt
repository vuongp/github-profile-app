package work.vuong.github_profile_app.screen.profile

import android.content.res.Resources
import androidx.recyclerview.widget.ConcatAdapter
import githubapi.GetUserQuery
import work.vuong.github_profile_app.R
import work.vuong.github_profile_app.common.adapterbinder.AdapterBinder
import work.vuong.github_profile_app.common.network.CoilImageLoader
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
): AdapterBinder<ConcatAdapter, GetUserQuery.User> {

    override fun bind(adapter: ConcatAdapter, item: GetUserQuery.User) {
        // TODO: 27/08/2021 refactor
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
                        item.name.orEmpty(),
                        item.login,
                        item.email,
                        item.followers.totalCount,
                        item.following.totalCount,
                    ).apply {
                        profileImageLoader = CoilImageLoader(item.avatarUrl.toString())
                    }
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

        val pinnedRepositories = item.pinnedItems.nodes?.mapNotNull { node ->
            node?.asRepository?.let {
                RepositoryItemAdapter.ViewItem(
                    it.owner.login,
                    it.name,
                    it.description.orEmpty(),
                    it.stargazerCount,
                    it.primaryLanguage?.name.orEmpty(),
                    it.primaryLanguage?.color.orEmpty(),
                ).apply {
                    repositoryImageLoader = CoilImageLoader(it.owner.avatarUrl.toString())
                }
            }
        }

        adapter.addAdapter(RepositoryItemAdapter().apply {
            submitList(pinnedRepositories)
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

        val topRepos = item.topRepositories.nodes?.mapNotNull { node ->
            node?.let {
                RepositoryItemAdapter.ViewItem(
                    it.owner.login,
                    it.name,
                    it.description.orEmpty(),
                    it.stargazerCount,
                    it.primaryLanguage?.name.orEmpty(),
                    it.primaryLanguage?.color.orEmpty(),
                ).apply {
                    repositoryImageLoader = CoilImageLoader(it.owner.avatarUrl.toString())
                }
            }
        }.orEmpty()
        adapter.addAdapter(HorizontalRepositoryListAdapter(horizontalRepositoryListDecorations).apply {
            submitList(listOf(HorizontalRepositoryListAdapter.ViewItem(topRepos)))
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

        val starredRepos = item.starredRepositories.nodes?.mapNotNull { node ->
            node?.let {
                RepositoryItemAdapter.ViewItem(
                    it.owner.login,
                    it.name,
                    it.description.orEmpty(),
                    it.stargazerCount,
                    it.primaryLanguage?.name.orEmpty(),
                    it.primaryLanguage?.color.orEmpty(),
                ).apply {
                    repositoryImageLoader = CoilImageLoader(it.owner.avatarUrl.toString())
                }
            }
        }.orEmpty()

        adapter.addAdapter(HorizontalRepositoryListAdapter(horizontalRepositoryListDecorations).apply {
            submitList(
                listOf(
                    HorizontalRepositoryListAdapter.ViewItem(starredRepos)
                )
            )
        })
    }

}