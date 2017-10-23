package com.mistreckless.support.musicapp.app

import android.app.Activity
import android.app.Application
import com.mistreckless.support.musicapp.domain.DataModule
import com.mistreckless.support.musicapp.domain.RepositoryModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by @mistreckless on 22.10.2017. !
 */
class App : Application(),HasActivityInjector {
    @Inject
    lateinit var activityDispatchAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> =activityDispatchAndroidInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .application(this)
                .dataModule(DataModule())
                .repositoryModule(RepositoryModule())
                .build()
                .inject(this)
    }
}