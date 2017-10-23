package com.mistreckless.support.musicapp.ui

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import io.reactivex.Observable
import io.reactivex.disposables.Disposables

/**
 * Created by mistreckless on 23.10.17.
 */

fun RecyclerView.observeScroll(): Observable<Int> = Observable.create { e ->
    if (!e.isDisposed)
        e.onNext(layoutManager.itemCount)

    val listener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            if (!e.isDisposed) {
                val position = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                val needToEmit = layoutManager.itemCount != 0 && position * 100 / layoutManager.itemCount > 70
                if (needToEmit)
                    e.onNext(layoutManager.itemCount)
            }
        }
    }
    this.addOnScrollListener(listener)
    e.setDisposable(Disposables.fromAction { this.removeOnScrollListener(listener) })
}