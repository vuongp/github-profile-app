package work.vuong.github_profile_app.screen.profile

import android.content.res.Resources
import androidx.recyclerview.widget.ConcatAdapter
import dagger.Module
import dagger.Provides
import work.vuong.github_profile_app.common.adapterbinder.AdapterBinder
import work.vuong.github_profile_app.common.decoration.DecorationProvider

@Module
class ProfileModule {

    @Provides
    fun providesResources(profileActivity: ProfileActivity): Resources = profileActivity.resources

    @Provides
    fun providesItemDecorations(): DecorationProvider = ProfileDecorationProvider()

    @Provides
    fun providesAdapterBinder(profileAdapterBinder: ProfileAdapterBinder): AdapterBinder<ConcatAdapter, Any> = profileAdapterBinder

}