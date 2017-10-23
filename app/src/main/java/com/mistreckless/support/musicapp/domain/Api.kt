@file:Suppress("NOTHING_TO_INLINE")

package com.mistreckless.support.musicapp.domain

import com.mistreckless.support.musicapp.domain.entity.TrackPlaylist
import com.mistreckless.support.musicapp.domain.entity.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Created by @mistreckless on 23.10.2017. !
 */

interface Api{

    @GET("/v1/me")
    fun getUserProfile(@Header("Authorization") token : String) : Single<User>

    @GET("/v1/me/tracks")
    fun getUserTracks(@Header("Authorization") token : String) : Single<TrackPlaylist>

}