package com.mistreckless.support.musicapp.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by @mistreckless on 22.10.2017. !
 */
abstract class BaseActivity<out P : BasePresenter<*,*>,F : BasePresenterProviderFactory<P>> : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var fragmentDispatcher: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var presenterProviderFactory : F

    val presenter by lazy { presenterProviderFactory.get() }


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initView()
        presenter.attachRouter(this)
        presenter.attachView(this)
        if (savedInstanceState == null) {
            presenter.onFirstViewAttached()
        } else presenter.onViewRestored(savedInstanceState)
    }


    private fun initView() {
        val cls = javaClass
        if (cls.isAnnotationPresent(Layout::class.java)) {
            val annotation = cls.getAnnotation(Layout::class.java)
            setContentView(annotation.id)
        }
    }

    override fun onDestroy() {
        presenter.detachRouter()
        presenter.detachView()
        super.onDestroy()
    }

    override fun supportFragmentInjector() = fragmentDispatcher
}