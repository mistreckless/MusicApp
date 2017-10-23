package com.mistreckless.support.musicapp.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mistreckless.support.musicapp.ui.main.MainActivityRouter
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * Created by @mistreckless on 22.10.2017. !
 */
abstract class BaseFragment<out P : BasePresenter<*, *>, F : BasePresenterProviderFactory<P>> : Fragment() {
    @Inject
    lateinit var presenterFactory : F
    val presenter : P by lazy { presenterFactory.get() }

    var restoredBundle : Bundle?=null

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        retainInstance = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        restoredBundle = savedInstanceState
        super.onCreate(savedInstanceState)
        presenter.attachRouter(getRouter())
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val cls = javaClass
        if (!cls.isAnnotationPresent(Layout::class.java)) return null
        val annotation = cls.getAnnotation(Layout::class.java)
        return inflater!!.inflate(annotation.id, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        presenter.attachRouter(getRouter())

        if (restoredBundle !=null) presenter.onViewRestoredWhenSystemKillAppOrActivity()
        else if(savedInstanceState==null) presenter.onFirstViewAttached()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        presenter.attachRouter(getRouter())
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        presenter.onViewRestored(savedInstanceState ?: Bundle())

    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    override fun onDestroy() {
        presenter.detachRouter()
        super.onDestroy()
    }

    protected open fun getRouter(): Any = activity as MainActivityRouter
}