package com.mistreckless.support.musicapp.ui.main

import android.content.Intent
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.mistreckless.support.musicapp.domain.repository.UserRepository
import com.mistreckless.support.musicapp.ui.BasePresenter
import com.mistreckless.support.musicapp.ui.PerActivity
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse.Type.*
import javax.inject.Inject

/**
 * Created by @mistreckless on 22.10.2017. !
 */
@PerActivity
@InjectViewState
class MainActivityPresenter @Inject constructor(private val userRepository: UserRepository) : BasePresenter<MainActivityView, MainActivityRouter>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
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
                                    viewState.initUi()
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
        getRouter()?.navigateToProfile()
    }
}

