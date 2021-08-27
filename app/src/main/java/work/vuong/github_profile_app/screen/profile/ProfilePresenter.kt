package work.vuong.github_profile_app.screen.profile

import githubapi.GetUserQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
import work.vuong.github_profile_app.BuildConfig
import work.vuong.github_profile_app.common.network.GitHubApiService
import java.lang.Exception
import javax.inject.Inject

@InjectViewState
class ProfilePresenter @Inject constructor(
    private val gitHubApiService: GitHubApiService
) : MvpPresenter<ProfileView>() {

    override fun onFirstViewAttach() {
        viewState.setRefreshing(true)
        fetchUser()
    }

    fun onRefresh() {
        fetchUser()
    }

    private fun fetchUser() {
        presenterScope.launch {
            try {
                val user: GetUserQuery.User? = gitHubApiService.fetchUser(BuildConfig.PROFILE)

                withContext(Dispatchers.Main) {
                    viewState.setRefreshing(false)
                    if (user != null) {
                        viewState.showProfile(user)
                    } else {
                        // TODO: 27/08/2021 error handling
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    viewState.setRefreshing(false)
                }
                // TODO: 27/08/2021 error handling
            }


        }
    }

}