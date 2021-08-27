package work.vuong.github_profile_app.common.network

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import githubapi.GetUserQuery
import work.vuong.github_profile_app.BuildConfig
import java.lang.Exception
import javax.inject.Inject

class ApolloGithubApiService @Inject constructor(
    private val apolloClient: ApolloClient
): GitHubApiService {

    override suspend fun fetchUser(username: String): GetUserQuery.User? {
        val response = apolloClient.query(GetUserQuery(login = BuildConfig.PROFILE)).await()

        val firstError = response.errors?.first()
        if (firstError != null) {
            throw Exception(firstError.message)
        }

        return response.data?.user
    }

}