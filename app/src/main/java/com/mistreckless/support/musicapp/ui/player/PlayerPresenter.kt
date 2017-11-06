package com.mistreckless.support.musicapp.ui.player

import com.arellomobile.mvp.InjectViewState
import com.mistreckless.support.musicapp.ui.BasePresenter
import com.mistreckless.support.musicapp.ui.PerActivity
import javax.inject.Inject

/**
 * Created by mistreckless on 23.10.17.
 */

@PerActivity
@InjectViewState
class PlayerPresenter @Inject constructor(): BasePresenter<PlayerActivityView,PlayerActivityRouter>(){
    override fun onFirstViewAttach() {
        viewState.initUi()
    }

    companion object {
        const val TAG ="PlayerPresenter"
    }

}
