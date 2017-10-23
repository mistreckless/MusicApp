package com.mistreckless.support.musicapp.domain.repository

import android.content.SharedPreferences
import com.mistreckless.support.musicapp.domain.Api
import com.mistreckless.support.musicapp.domain.entity.TrackPlaylist
import com.mistreckless.support.musicapp.domain.entity.User
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by @mistreckless on 23.10.2017. !
 */

interface UserRepository {
    fun getUser(): Single<User>
    fun cacheToken(token: String)
    fun getToken(): String
    fun getUserTracks(offset : Int=0, limit : Int=20) : Single<TrackPlaylist>
//    fun getTracks(playlistId : String) : Single<List<TrackPlaylist>>
}


class UserRepositoryImpl(private val api: Api, private val preferences: SharedPreferences) : UserRepository {
    override fun getUserTracks(offset: Int,limit: Int): Single<TrackPlaylist> {
        return api.getUserTracks("Bearer "+getToken(),offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getUser(): Single<User> {
        val token = preferences.getString("token", "")
        return api.getUserProfile("Bearer "+token)
                .doOnSuccess {cacheUserId(it.id) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun cacheToken(token: String)
            = preferences.edit().putString("token", token).apply()

    override fun getToken(): String = preferences.getString("token", "")

    private fun cacheUserId(id: String) {
        preferences.edit().putString("id", id).apply()
    }
    private fun getUserId() = preferences.getString("id","")
}