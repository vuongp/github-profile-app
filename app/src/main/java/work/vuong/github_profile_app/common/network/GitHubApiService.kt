package work.vuong.github_profile_app.common.network

import githubapi.GetUserQuery

interface GitHubApiService {

    suspend fun fetchUser(username: String): GetUserQuery.User?

}