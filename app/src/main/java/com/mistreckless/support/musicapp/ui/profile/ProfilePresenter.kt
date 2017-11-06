package com.mistreckless.support.musicapp.ui.profile

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.mistreckless.support.musicapp.domain.repository.UserRepository
import com.mistreckless.support.musicapp.ui.BasePresenter
import com.mistreckless.support.musicapp.ui.PerFragment
import com.mistreckless.support.musicapp.ui.main.MainActivityRouter
import javax.inject.Inject

/**
 * Created by @mistreckless on 23.10.2017. !
 */
@PerFragment
@InjectViewState
class ProfilePresenter @Inject constructor(private val userRepository: UserRepository) : BasePresenter<ProfileView,MainActivityRouter>(){

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLoading(true)
        userRepository.getUser()
                .subscribe({
                    viewState.showLoading(false)
                    viewState.initUi(it.id)
                },{
                    Log.e(TAG,it.message)
                })

    }

    companion object {
        const val TAG = "ProfilePresenter"
    }
}
