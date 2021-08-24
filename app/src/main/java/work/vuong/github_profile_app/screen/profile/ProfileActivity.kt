package work.vuong.github_profile_app.screen.profile

import android.os.Bundle
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import work.vuong.github_profile_app.R

class ProfileActivity : MvpAppCompatActivity(), ProfileView {

    private val presenter by moxyPresenter { ProfilePresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showProfile(any: Any) {
        TODO("Not yet implemented")
    }

}