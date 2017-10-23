package com.mistreckless.support.musicapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * Created by @mistreckless on 22.10.2017. !
 */
class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent==null) return
        val timeSentInMs = intent.getLongExtra("timeSent",0L)
        val action = intent.action!!

        when(action){
            METADATA_CHANGED->{
                val trackId = intent.getStringExtra("id")
                val artistName = intent.getStringExtra("artist")
                val albumName = intent.getStringExtra("album")
                val trackName = intent.getStringExtra("track")
                val trackLengthInSec = intent.getIntExtra("length", 0)
            }
            PLAYBACK_STATE_CHANGED->{
                val playing = intent.getBooleanExtra("playing", false)
                val positionInMs = intent.getIntExtra("playbackPosition", 0)
            }
            QUEUE_CHANGED ->{

            }
        }
    }

    companion object {
        val SPOTIFY_PACKAGE = "com.spotify.music"
        val PLAYBACK_STATE_CHANGED = SPOTIFY_PACKAGE + ".playbackstatechanged"
        val QUEUE_CHANGED = SPOTIFY_PACKAGE + ".queuechanged"
        val METADATA_CHANGED = SPOTIFY_PACKAGE + ".metadatachanged"
    }
}