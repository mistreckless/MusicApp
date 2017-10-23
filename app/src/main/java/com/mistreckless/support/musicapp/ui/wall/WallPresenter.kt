package com.mistreckless.support.musicapp.ui.wall

import android.util.Log
import com.mistreckless.support.musicapp.domain.repository.UserRepository
import com.mistreckless.support.musicapp.ui.BasePresenter
import com.mistreckless.support.musicapp.ui.BasePresenterProviderFactory
import com.mistreckless.support.musicapp.ui.PerFragment
import com.mistreckless.support.musicapp.ui.main.MainActivityRouter
import com.mistreckless.support.musicapp.ui.presenterHolder
import javax.inject.Inject

/**
 * Created by @mistreckless on 22.10.2017. !
 */


class WallPresenter(private val userRepository: UserRepository) : BasePresenter<WallView,MainActivityRouter>(){
    override fun onFirstViewAttached() {
        userRepository.getUserTracks()
                .subscribe({ lib->
                   lib.items.map { it.track }.forEach { Log.e(TAG,it.name + " "+it.previewUrl) }
                },{
                    Log.e(TAG,it.message)
                })
    }

    companion object {
        const val TAG="WallPresenter"
    }

}
@PerFragment
class WallPresenterProviderFactory @Inject constructor(private val userRepository: UserRepository) : BasePresenterProviderFactory<WallPresenter>{
    override fun get(): WallPresenter {
        if (presenterHolder.contains(WallPresenter.TAG))
            return presenterHolder[WallPresenter.TAG] as WallPresenter
        val presenter = WallPresenter(userRepository)
        presenterHolder.put(WallPresenter.TAG,presenter)
        return presenter
    }

}