package com.mistreckless.support.musicapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.MvpView
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by @mistreckless on 22.10.2017. !
 */
abstract class BaseFragment<P : BasePresenter<out MvpView, *>> : MvpAppCompatFragment() {

    @Inject
    lateinit var presenterProvider: Provider<P>

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        retainInstance = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        attachRouter()
    }

    override fun onDestroy() {
        detachRouter()
        super.onDestroy()
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val cls = javaClass
        if (!cls.isAnnotationPresent(Layout::class.java)) return null
        val annotation = cls.getAnnotation(Layout::class.java)
        return inflater!!.inflate(annotation.id, container, false)
    }

    abstract fun attachRouter()
    abstract fun detachRouter()
}