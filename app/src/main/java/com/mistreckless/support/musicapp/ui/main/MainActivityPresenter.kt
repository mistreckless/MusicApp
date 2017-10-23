package com.mistreckless.support.musicapp.ui.main

import android.content.Intent
import android.util.Log
import com.mistreckless.support.musicapp.domain.repository.UserRepository
import com.mistreckless.support.musicapp.ui.BasePresenter
import com.mistreckless.support.musicapp.ui.BasePresenterProviderFactory
import com.mistreckless.support.musicapp.ui.PerActivity
import com.mistreckless.support.musicapp.ui.presenterHolder
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse.Type.*
import javax.inject.Inject

/**
 * Created by @mistreckless on 22.10.2017. !
 */
class MainActivityPresenter(private val userRepository: UserRepository) : BasePresenter<MainActivityView, MainActivityRouter>() {
    override fun onFirstViewAttached() {
        val builder = AuthenticationRequest.Builder(CLIENT_ID, TOKEN, REDIRECT_URL)
        builder.setScopes(arrayOf("streaming","user-library-read","user-read-birthdate","user-read-playback-state"))
        val request = builder.build()
        getRouter()?.navigateToLoginActivity(request, LOGIN_REQUEST_CODE)
    }


    fun activityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            LOGIN_REQUEST_CODE -> {
                val response = AuthenticationClient.getResponse(resultCode, data)
                when (response.type!!) {
                    TOKEN -> {
                        userRepository.cacheToken(response.accessToken)
                        userRepository.getUser()
                                .subscribe({
                                    getView()?.initUi()
                                    getRouter()?.navigateToProfile()
                                }, {
                                    Log.e("error", it.message)
                                })
                    }
                    CODE -> TODO()
                    ERROR -> Log.e(TAG, response.error)
                    EMPTY -> TODO()
                    UNKNOWN -> TODO()
                }
            }
        }
    }


    companion object {
        const val TAG = "MainActivityPresenter"
        const val CLIENT_ID = "dc0765802b28449baff71bdedfa2b723"
        const val REDIRECT_URL = "zaebalo://callback"
        const val LOGIN_REQUEST_CODE = 1
    }

    fun tabPlaylistClicked() {
        getRouter()?.navigateToWall()
    }

    fun taProfileClicked() {

    }
}

@PerActivity
class MainPresenterProviderFactory @Inject constructor(private val userRepository: UserRepository) : BasePresenterProviderFactory<MainActivityPresenter> {
    override fun get(): MainActivityPresenter {
        if (presenterHolder.contains(MainActivityPresenter.TAG))
            return presenterHolder[MainActivityPresenter.TAG] as MainActivityPresenter
        val presenter = MainActivityPresenter(userRepository)
        presenterHolder.put(MainActivityPresenter.TAG, presenter)
        return presenter
    }

}

