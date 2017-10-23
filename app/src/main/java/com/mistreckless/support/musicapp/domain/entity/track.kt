package com.mistreckless.support.musicapp.domain.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by @mistreckless on 23.10.2017. !
 */

data class Track(val uri : String ="",
                 @SerializedName("preview_url")val previewUrl : String="",
                 val name : String="",
                 val id : String="") : Serializable

data class TrackPlaylist(val items : List<TrackItem> =listOf())

data class TrackItem(val track : Track=Track())