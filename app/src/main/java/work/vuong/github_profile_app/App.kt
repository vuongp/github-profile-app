package work.vuong.github_profile_app

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import work.vuong.github_profile_app.common.di.ApplicationModule
import work.vuong.github_profile_app.common.di.DaggerAppComponent

open class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

}