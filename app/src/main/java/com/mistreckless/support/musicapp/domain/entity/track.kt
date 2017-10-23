package com.mistreckless.support.musicapp.domain.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by @mistreckless on 23.10.2017. !
 */

data class Track(val uri : String ="",
                 @SerializedName("preview_url")val previewUrl : String="",
                 val name : String="",
                 val id : String="")

data class TrackPlaylist(val items : List<TrackItem> =listOf())

data class TrackItem(val track : Track=Track())