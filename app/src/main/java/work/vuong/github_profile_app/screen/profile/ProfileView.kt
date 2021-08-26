package work.vuong.github_profile_app.screen.profile

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface ProfileView : MvpView {

    @AddToEndSingle
    fun showProfile()

    @Skip
    fun setRefreshing(isRefreshing: Boolean)

}