package work.vuong.github_profile_app.screen.profile

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import work.vuong.github_profile_app.R
import work.vuong.github_profile_app.databinding.ActivityProfileBinding
import work.vuong.view_components.common.decoration.LastItemDecoration
import work.vuong.view_components.common.decoration.MarginByTypeDecoration
import work.vuong.view_components.common.decoration.SizeDecoration
import work.vuong.view_components.profile.*

class ProfileActivity : MvpAppCompatActivity(), ProfileView {

    private lateinit var binding: ActivityProfileBinding

    private val presenter by moxyPresenter { ProfilePresenter() }

    private val adapter by lazy {
        ConcatAdapter(
            ConcatAdapter.Config.Builder()
                .setIsolateViewTypes(false)
                .build()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupSwipeToRefresh()
    }

    private fun setupSwipeToRefresh() {
        binding.swipeToRefresh.setOnRefreshListener {
            presenter.onRefresh()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // TODO: 26/08/2021 Move
        val materialPoint2 = resources.getDimensionPixelSize(R.dimen.material_point_2)
        val materialPoint3 = resources.getDimensionPixelSize(R.dimen.material_point_3)

        binding.recyclerView.addItemDecoration(MarginByTypeDecoration(
            R.layout.profile_header,
            R.layout.category_title,
            left = materialPoint2,
            right = materialPoint2,
            top = materialPoint3
        ))

        binding.recyclerView.addItemDecoration(MarginByTypeDecoration(
            R.layout.repository_item,
            left = materialPoint2,
            right = materialPoint2,
            top = materialPoint2
        ))

        binding.recyclerView.addItemDecoration(MarginByTypeDecoration(
            R.layout.horizontal_repository_list,
            top = materialPoint2
        ))

        binding.recyclerView.addItemDecoration(LastItemDecoration(
            bottom = materialPoint2
        ))
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun showProfile() {
        // TODO: 26/08/2021 Move
        adapter.adapters.forEach {
            adapter.removeAdapter(it)
        }
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

    override fun setRefreshing(isRefreshing: Boolean) {
        binding.swipeToRefresh.isRefreshing = isRefreshing
    }

}