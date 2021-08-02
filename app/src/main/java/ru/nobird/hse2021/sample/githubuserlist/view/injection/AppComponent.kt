package ru.nobird.hse2021.sample.githubuserlist.view.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.nobird.hse2021.sample.githubuserlist.presentation.ui.activity.GithubUserListActivity
import javax.inject.Singleton

@Component(modules = [
    NetworkModule::class
])
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }

    fun githubUserListComponentBuilder(): GithubUserListComponent.Builder
}