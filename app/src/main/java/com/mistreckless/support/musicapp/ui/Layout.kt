package com.mistreckless.support.musicapp.ui

import android.support.annotation.LayoutRes

/**
 * Created by @mistreckless on 22.10.2017. !
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Layout(@LayoutRes val id : Int)