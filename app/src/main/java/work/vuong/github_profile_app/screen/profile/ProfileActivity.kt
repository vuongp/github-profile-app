package work.vuong.github_profile_app.screen.profile

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import githubapi.GetUserQuery
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import work.vuong.github_profile_app.common.adapterbinder.AdapterBinder
import work.vuong.github_profile_app.common.decoration.DecorationProvider
import work.vuong.github_profile_app.databinding.ActivityProfileBinding
import javax.inject.Inject
import javax.inject.Provider

class ProfileActivity : MvpAppCompatActivity(), ProfileView {

    @Inject
    lateinit var itemDecorations: DecorationProvider

    @Inject
    lateinit var presenterProvider: Provider<ProfilePresenter>

    @Inject
    lateinit var adapterBinder: AdapterBinder<ConcatAdapter, GetUserQuery.User>

    private lateinit var binding: ActivityProfileBinding

    private val presenter by moxyPresenter { presenterProvider.get() }

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

        itemDecorations.provide(resources).forEach {
            binding.recyclerView.addItemDecoration(it)
        }
        adapterBinder.bind(adapter)
    }

    override fun showProfile(user: GetUserQuery.User) {
        adapterBinder.update(adapter, user)
    }

    override fun setRefreshing(isRefreshing: Boolean) {
        binding.swipeToRefresh.isRefreshing = isRefreshing
    }

}