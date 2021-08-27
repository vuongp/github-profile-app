package work.vuong.github_profile_app.common.di

import android.content.Context
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.cache.http.HttpCachePolicy
import com.apollographql.apollo.cache.http.ApolloHttpCache
import com.apollographql.apollo.cache.http.DiskLruHttpCacheStore
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import work.vuong.github_profile_app.BuildConfig
import work.vuong.github_profile_app.common.network.AuthorizationHeaderInterceptor
import java.io.File
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    fun providesApolloClient(context: Context): ApolloClient {
        val file = File(context.cacheDir, "apolloCache")
        val size: Long = 1024 * 1024 * 5
        val cacheStore = DiskLruHttpCacheStore(file, size)

        return ApolloClient.builder()
            .serverUrl("https://api.github.com/graphql")
            .httpCache(ApolloHttpCache(cacheStore))
            .defaultHttpCachePolicy(
                HttpCachePolicy.Policy(
                    HttpCachePolicy.FetchStrategy.NETWORK_FIRST,
                    1,
                    TimeUnit.DAYS,
                    false
                )
            )
            .okHttpClient(
                OkHttpClient.Builder()
                    .addInterceptor(
                        AuthorizationHeaderInterceptor("Bearer ${BuildConfig.GITHUB_API_TOKEN}")
                    )
                    .build()
            )
            .build()
    }

}