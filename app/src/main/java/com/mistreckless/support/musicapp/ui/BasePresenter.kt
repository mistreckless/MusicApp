package com.mistreckless.support.musicapp.ui

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by @mistreckless on 22.10.2017. !
 */
@Suppress("UNCHECKED_CAST")
abstract class BasePresenter<V : MvpView, out R> : MvpPresenter<V>() {
    private var router: R? = null
    protected val viewChangesDisposables by lazy { CompositeDisposable() }

    fun attachRouter(router: Any) {
        this.router = router as R
    }

    fun detachRouter() {
        router = null
    }

    fun getRouter() = router

    override fun destroyView(view: V) {
        viewChangesDisposables.clear()
        super.destroyView(view)
    }

}


