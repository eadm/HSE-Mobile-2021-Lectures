package ru.nobird.hse2021.sample

import android.app.Application
import ru.nobird.hse2021.sample.githubuserlist.view.injection.AppComponent
import ru.nobird.hse2021.sample.githubuserlist.view.injection.DaggerAppComponent

class App : Application() {
    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }

    fun requireComponent(): AppComponent =
        component
}