package com.mistreckless.support.musicapp.ui.wall

import android.util.Log
import com.mistreckless.support.musicapp.domain.entity.Track
import com.mistreckless.support.musicapp.domain.entity.TrackPlaylist
import com.mistreckless.support.musicapp.domain.repository.UserRepository
import com.mistreckless.support.musicapp.ui.BasePresenter
import com.mistreckless.support.musicapp.ui.BasePresenterProviderFactory
import com.mistreckless.support.musicapp.ui.PerFragment
import com.mistreckless.support.musicapp.ui.main.MainActivityRouter
import com.mistreckless.support.musicapp.ui.presenterHolder
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by @mistreckless on 22.10.2017. !
 */


class WallPresenter(private val userRepository: UserRepository) : BasePresenter<WallView, MainActivityRouter>() {
    override fun onFirstViewAttached() {
        getView()?.initUi()
        getView()?.showProgressBar(true)
    }

    companion object {
        const val TAG = "WallPresenter"
    }

    fun controlList(observeScroll: Observable<Int>) {
        viewChangesDisposables.add(observeScroll.distinctUntilChanged()
                .flatMapSingle { userRepository.getUserTracks(it) }
                .subscribe({ t: TrackPlaylist ->
                    getView()?.showProgressBar(false)
                    getView()?.setItemsToAdapter(t.items.map { it.track })
                }, {
                    getView()?.showProgressBar(false)
                    Log.e(TAG, it.message)
                }))
    }

    fun songClicked(track: Track) {
        getRouter()?.navigateToPlayer(track)
    }

}

@PerFragment
class WallPresenterProviderFactory @Inject constructor(private val userRepository: UserRepository) : BasePresenterProviderFactory<WallPresenter> {
    override fun get(): WallPresenter {
        if (presenterHolder.contains(WallPresenter.TAG))
            return presenterHolder[WallPresenter.TAG] as WallPresenter
        val presenter = WallPresenter(userRepository)
        presenterHolder.put(WallPresenter.TAG, presenter)
        return presenter
    }

}