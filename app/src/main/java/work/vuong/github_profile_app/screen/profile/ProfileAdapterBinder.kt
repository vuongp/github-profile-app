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
import work.vuong.view_components.common.helper.submitItem
import work.vuong.view_components.profile.CategoryTitleAdapter
import work.vuong.view_components.profile.HorizontalRepositoryListAdapter
import work.vuong.view_components.profile.ProfileHeaderAdapter
import work.vuong.view_components.profile.RepositoryItemAdapter
import work.vuong.view_components.profile.TopBarTitleAdapter
import javax.inject.Inject

class ProfileAdapterBinder @Inject constructor(
    private val resources: Resources
): AdapterBinder<ConcatAdapter, GetUserQuery.User> {

    private val materialPoint2 = resources.getDimensionPixelSize(R.dimen.material_point_2)
    private val smallRepositoryWidth = resources.getDimensionPixelSize(R.dimen.small_repository_width)
    private val horizontalRepositoryListDecorations = listOf(
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

    private val topBarTitleAdapter = TopBarTitleAdapter(TopBarTitleAdapter.ViewItem(resources.getString(R.string.profile_title)))
    private val profileHeaderAdapter = ProfileHeaderAdapter()
    private val pinnedCategoryTitleAdapter = CategoryTitleAdapter()
    private val pinnedRepositoryItemAdapter = RepositoryItemAdapter()
    private val topCategoryTitleAdapter = CategoryTitleAdapter()
    private val topRepositoryListAdapter = HorizontalRepositoryListAdapter(horizontalRepositoryListDecorations)
    private val starredCategoryTitleAdapter = CategoryTitleAdapter()
    private val starredRepositoryListAdapter = HorizontalRepositoryListAdapter(horizontalRepositoryListDecorations)

    override fun bind(adapter: ConcatAdapter) {
        adapter.adapters.forEach {
            adapter.removeAdapter(it)
        }
        adapter.addAdapter(topBarTitleAdapter)
        adapter.addAdapter(profileHeaderAdapter)
        adapter.addAdapter(pinnedCategoryTitleAdapter)
        adapter.addAdapter(pinnedRepositoryItemAdapter)
        adapter.addAdapter(topCategoryTitleAdapter)
        adapter.addAdapter(topRepositoryListAdapter)
        adapter.addAdapter(starredCategoryTitleAdapter)
        adapter.addAdapter(starredRepositoryListAdapter)
    }

    override fun update(adapter: ConcatAdapter, item: GetUserQuery.User) {
        profileHeaderAdapter.submitList(listOf(createProfileHeaderViewItem(item)))

        val pinnedRepositories = item.pinnedItems.nodes?.mapNotNull { node ->
            node?.asRepository?.let { createRepositoryViewItem(it) }
        }.orEmpty()
        if (pinnedRepositories.isEmpty()) {
            pinnedCategoryTitleAdapter.submitList(emptyList())
        } else {
            pinnedCategoryTitleAdapter.submitItem(
                CategoryTitleAdapter.ViewItem(
                    resources.getString(R.string.pinned_repositories_title)
                )
            )
        }
        pinnedRepositoryItemAdapter.submitList(pinnedRepositories)


        val topRepos = item.topRepositories.nodes?.mapNotNull { node ->
            node?.let { createRepositoryViewItem(it) }
        }.orEmpty()
        if (topRepos.isEmpty()) {
            topCategoryTitleAdapter.submitList(emptyList())
        } else {
            topCategoryTitleAdapter.submitItem(
                CategoryTitleAdapter.ViewItem(
                    resources.getString(R.string.top_repositories_title)
                )
            )
        }
        topRepositoryListAdapter.submitItem(HorizontalRepositoryListAdapter.ViewItem(topRepos))

        val starredRepos = item.starredRepositories.nodes?.mapNotNull { node ->
            node?.let { createRepositoryViewItem(it) }
        }.orEmpty()
        if (starredRepos.isEmpty()) {
            starredCategoryTitleAdapter.submitList(emptyList())
        } else {
            starredCategoryTitleAdapter.submitItem(
                CategoryTitleAdapter.ViewItem(
                    resources.getString(R.string.starred_repositories_title)
                )
            )
        }
        starredRepositoryListAdapter.submitItem(HorizontalRepositoryListAdapter.ViewItem(starredRepos))
    }

    private fun createProfileHeaderViewItem(item: GetUserQuery.User): ProfileHeaderAdapter.ViewItem {
        return ProfileHeaderAdapter.ViewItem(
            item.name.orEmpty(),
            item.login,
            item.email,
            item.followers.totalCount,
            item.following.totalCount,
        ).apply {
            profileImageLoader = CoilImageLoader(item.avatarUrl.toString())
        }
    }

    // FIXME: 27/08/2021 have repository graphql classes share the same data
    private fun createRepositoryViewItem(it: GetUserQuery.AsRepository): RepositoryItemAdapter.ViewItem {
        return RepositoryItemAdapter.ViewItem(
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

    private fun createRepositoryViewItem(it: GetUserQuery.Node1): RepositoryItemAdapter.ViewItem {
        return RepositoryItemAdapter.ViewItem(
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

    private fun createRepositoryViewItem(it: GetUserQuery.Node2): RepositoryItemAdapter.ViewItem {
        return RepositoryItemAdapter.ViewItem(
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
