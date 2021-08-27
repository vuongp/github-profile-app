package work.vuong.github_profile_app.common.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationHeaderInterceptor(
    val headerValue: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", headerValue)
            .build()

        return chain.proceed(request)
    }

}