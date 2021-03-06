package work.vuong.github_profile_app.common.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import work.vuong.github_profile_app.App
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    ActivityInjectionModule::class,
    NetworkModule::class,
    ApplicationModule::class
])
interface AppComponent: AndroidInjector<App>