package com.mistreckless.support.musicapp.app

import android.app.Application
import android.content.Context
import com.mistreckless.support.musicapp.domain.DataModule
import com.mistreckless.support.musicapp.domain.RepositoryModule
import com.mistreckless.support.musicapp.ui.PerActivity
import com.mistreckless.support.musicapp.ui.main.MainActivity
import com.mistreckless.support.musicapp.ui.main.MainActivityFragmentProvider
import com.mistreckless.support.musicapp.ui.player.PlayerActivity
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

/**
 * Created by @mistreckless on 22.10.2017. !
 */
@Singleton
@Module
class AppModule {

    @Provides
    fun providesContext(application: Application) : Context = application
}

@PerActivity
@Module
abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(MainActivityFragmentProvider::class))
    abstract fun bindMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(MainActivityFragmentProvider::class))
    abstract fun bindPlayerActivity() : PlayerActivity

}

@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class,
        RepositoryModule::class,
        DataModule::class,
        ActivityBuilder::class))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) : Builder
        fun repositoryModule(repositoryModule: RepositoryModule) : Builder
        fun dataModule(dataModule: DataModule) : Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
}