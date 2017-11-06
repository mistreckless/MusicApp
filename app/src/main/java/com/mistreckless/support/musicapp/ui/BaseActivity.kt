package com.mistreckless.support.musicapp.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.MvpView
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by @mistreckless on 22.10.2017. !
 */
abstract class BaseActivity<P : BasePresenter<out MvpView,*>> : MvpAppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var fragmentDispatcher: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var presenterProvider: Provider<P>


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        attachRouter()
        initView()
    }


    private fun initView() {
        val cls = javaClass
        if (cls.isAnnotationPresent(Layout::class.java)) {
            val annotation = cls.getAnnotation(Layout::class.java)
            setContentView(annotation.id)
        }
    }

    override fun onDestroy() {
        detachRouter()
        super.onDestroy()
    }

    abstract fun attachRouter()
    abstract fun detachRouter()

    override fun supportFragmentInjector() = fragmentDispatcher
}