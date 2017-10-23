package com.mistreckless.support.musicapp.ui.profile

import android.util.Log
import com.mistreckless.support.musicapp.domain.repository.UserRepository
import com.mistreckless.support.musicapp.ui.BasePresenter
import com.mistreckless.support.musicapp.ui.BasePresenterProviderFactory
import com.mistreckless.support.musicapp.ui.PerFragment
import com.mistreckless.support.musicapp.ui.main.MainActivityRouter
import com.mistreckless.support.musicapp.ui.presenterHolder
import javax.inject.Inject

/**
 * Created by @mistreckless on 23.10.2017. !
 */

class ProfilePresenter(private val userRepository: UserRepository) : BasePresenter<ProfileView,MainActivityRouter>(){
    override fun onFirstViewAttached() {
        getView()?.showLoading(true)
        userRepository.getUser()
                .subscribe({
                    getView()?.showLoading(false)
                    getView()?.initUi(it.id)
                },{
                    Log.e(TAG,it.message)
                })
    }

    companion object {
        const val TAG = "ProfilePresenter"
    }
}
@PerFragment
class ProfilePresenterProviderFactory @Inject constructor(private val userRepository: UserRepository) : BasePresenterProviderFactory<ProfilePresenter>{
    override fun get(): ProfilePresenter {
        if (presenterHolder.contains(ProfilePresenter.TAG))
            return presenterHolder[ProfilePresenter.TAG] as ProfilePresenter
        val presenter = ProfilePresenter(userRepository)
        presenterHolder.put(ProfilePresenter.TAG,presenter)
        return presenter
    }

}