package com.mistreckless.support.musicapp.ui.player

import com.mistreckless.support.musicapp.ui.BasePresenter
import com.mistreckless.support.musicapp.ui.BasePresenterProviderFactory
import com.mistreckless.support.musicapp.ui.presenterHolder
import javax.inject.Inject

/**
 * Created by mistreckless on 23.10.17.
 */

class PlayerPresenter : BasePresenter<PlayerActivityView,PlayerActivityRouter>(){
    override fun onFirstViewAttached() {
        getView()?.initUi()
    }

    companion object {
        const val TAG ="PlayerPresenter"
    }

}

class PlayerPresenterProviderFactory @Inject constructor() : BasePresenterProviderFactory<PlayerPresenter>{
    override fun get(): PlayerPresenter {
        if (presenterHolder.contains(PlayerPresenter.TAG))
            return presenterHolder[PlayerPresenter.TAG] as PlayerPresenter
        val presenter = PlayerPresenter()
        presenterHolder.put(PlayerPresenter.TAG,presenter)
        return presenter
    }

}