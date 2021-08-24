package work.vuong.github_profile_app.screen.profile

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface ProfileView : MvpView {

    @AddToEndSingle
    fun showProfile(any: Any)

}