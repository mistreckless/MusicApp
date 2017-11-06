package com.mistreckless.support.musicapp.ui.wall

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.mistreckless.support.musicapp.domain.entity.Track
import com.mistreckless.support.musicapp.domain.entity.TrackPlaylist
import com.mistreckless.support.musicapp.domain.repository.UserRepository
import com.mistreckless.support.musicapp.ui.BasePresenter
import com.mistreckless.support.musicapp.ui.PerFragment
import com.mistreckless.support.musicapp.ui.main.MainActivityRouter
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by @mistreckless on 22.10.2017. !
 */

@PerFragment
@InjectViewState
class WallPresenter @Inject constructor(private val userRepository: UserRepository) : BasePresenter<WallView, MainActivityRouter>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initUi()
        viewState.showProgressBar(true)
    }

    companion object {
        const val TAG = "WallPresenter"
    }

    fun controlList(observeScroll: Observable<Int>) {
        viewChangesDisposables.add(observeScroll.distinctUntilChanged()
                .flatMapSingle { userRepository.getUserTracks(it) }
                .subscribe({ t: TrackPlaylist ->
                    viewState.showProgressBar(false)
                    viewState.setItemsToAdapter(t.items.map { it.track })
                }, {
                    viewState.showProgressBar(false)
                    Log.e(TAG, it.message)
                }))
    }

    fun songClicked(track: Track) {
        getRouter()?.navigateToPlayer(track)
    }

}