package work.vuong.github_profile_app.common.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(val context: Context) {

    @Provides
    fun provideContext(): Context = context

}