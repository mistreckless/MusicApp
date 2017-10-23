package com.mistreckless.support.musicapp.ui.main

import com.mistreckless.support.musicapp.ui.PerFragment
import com.mistreckless.support.musicapp.ui.profile.Profile
import com.mistreckless.support.musicapp.ui.wall.Wall
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by @mistreckless on 22.10.2017. !
 */
//@Module
//class MainActivityModule{
//
//}

@Module
abstract class MainActivityFragmentProvider{

    @PerFragment
    @ContributesAndroidInjector
    abstract fun provideWall() : Wall

    @PerFragment
    @ContributesAndroidInjector
    abstract fun provideProfile() : Profile
}