package work.vuong.github_profile_app.screen.profile

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import work.vuong.github_profile_app.common.adapterbinder.AdapterBinder
import work.vuong.github_profile_app.common.decoration.DecorationProvider
import work.vuong.github_profile_app.databinding.ActivityProfileBinding
import javax.inject.Inject

class ProfileActivity : MvpAppCompatActivity(), ProfileView {

    @Inject
    lateinit var itemDecorations: DecorationProvider

    @Inject
    lateinit var adapterBinder: AdapterBinder<ConcatAdapter, Any>

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

        itemDecorations.provide(resources).forEach {
            binding.recyclerView.addItemDecoration(it)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun showProfile() {
        adapter.adapters.forEach {
            adapter.removeAdapter(it)
        }
        adapterBinder.bind(adapter, Any())
        adapter.notifyDataSetChanged()
    }

    override fun setRefreshing(isRefreshing: Boolean) {
        binding.swipeToRefresh.isRefreshing = isRefreshing
    }

}