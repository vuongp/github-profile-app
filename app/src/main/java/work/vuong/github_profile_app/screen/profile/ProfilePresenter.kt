package work.vuong.github_profile_app.screen.profile

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import githubapi.GetUserQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
import work.vuong.github_profile_app.BuildConfig
import javax.inject.Inject

@InjectViewState
class ProfilePresenter @Inject constructor(
    private val apolloClient: ApolloClient
) : MvpPresenter<ProfileView>() {

    override fun onFirstViewAttach() {
        viewState.setRefreshing(true)
        fetchUser()
    }

    fun onRefresh() {
        fetchUser()
    }

    private fun fetchUser() {
        presenterScope.launch(Dispatchers.IO) {
            val response = try {
                apolloClient.query(GetUserQuery(login = BuildConfig.PROFILE)).await()
            } catch (e: ApolloException) {
                // TODO: 27/08/2021 handle protocol errors
                return@launch
            }

            val user = response.data?.user
            if (user == null || response.hasErrors()) {
                // TODO: 27/08/2021 handle application errors
                return@launch
            }

            withContext(Dispatchers.Main) {
                viewState.setRefreshing(false)
                viewState.showProfile(user)
            }
        }
    }

}