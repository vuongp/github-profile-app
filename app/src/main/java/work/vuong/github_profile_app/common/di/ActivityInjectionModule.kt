package work.vuong.github_profile_app.common.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import work.vuong.github_profile_app.screen.profile.ProfileActivity
import work.vuong.github_profile_app.screen.profile.ProfileModule

@Module
abstract class ActivityInjectionModule {

    @ContributesAndroidInjector(modules = [ProfileModule::class])
    abstract fun injectProfileActivity(): ProfileActivity

}