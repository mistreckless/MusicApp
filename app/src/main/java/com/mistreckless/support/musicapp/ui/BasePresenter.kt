package com.mistreckless.support.musicapp.ui

import android.os.Bundle
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by @mistreckless on 22.10.2017. !
 */
@Suppress("UNCHECKED_CAST")
abstract class BasePresenter<out V, out R> {
    private var router: R? = null
    private var view: V? = null
    protected val viewChangesDisposables by lazy { CompositeDisposable() }

    fun attachRouter(router: Any) {
        this.router = router as R
    }

    fun detachRouter() {
        router = null
    }

    fun getRouter() = router

    fun attachView(view: Any) {
        this.view = view as V
    }

    fun detachView() {
        onViewDetached()
        this.view = null
    }

    fun getView() = view

    abstract fun onFirstViewAttached()

    open fun onViewDetached(){
        viewChangesDisposables.clear()
    }

    open fun onViewRestored(saveInstanceState: Bundle) {}

    open fun onViewRestoredWhenSystemKillAppOrActivity(){}
}

interface BasePresenterProviderFactory<out T : BasePresenter<*,*>>{
    fun get() : T
}

val presenterHolder: MutableMap<String, BasePresenter<*, *>> = HashMap()