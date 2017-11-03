package com.mistreckless.support.musicapp.ui.player

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Handler
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.mistreckless.support.musicapp.R
import com.mistreckless.support.musicapp.domain.entity.Track
import com.mistreckless.support.musicapp.ui.BaseActivity
import com.mistreckless.support.musicapp.ui.Layout
import com.spotify.sdk.android.player.Player
import kotlinx.android.synthetic.main.activity_player.*


@Layout(R.layout.activity_player)
class PlayerActivity : BaseActivity<PlayerPresenter,PlayerPresenterProviderFactory>(),PlayerActivityView,PlayerActivityRouter {

    lateinit var player : Player

    @SuppressLint("SetJavaScriptEnabled")
    override fun initUi() {
        val track = intent.getSerializableExtra("track") as Track
        val mainHandler = Handler()
        val bandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)

// 2. Create the player
        val player = ExoPlayerFactory.newSimpleInstance(this, trackSelector)

        playerView.player=player

        // Measures bandwidth during playback. Can be null if not required.
// Produces DataSource instances through which media data is loaded.
        val dataSourceFactory = DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "MusicApp"), bandwidthMeter)
// Produces Extractor instances for parsing the media data.
        val extractorsFactory = DefaultExtractorsFactory()
// This is the MediaSource representing the media to be played.
        val videoSource = ExtractorMediaSource(Uri.parse(track.previewUrl),
                dataSourceFactory, extractorsFactory, null, null)
// Prepare the player with the source.
        player.prepare(videoSource)

    }

}

interface PlayerActivityView {
    fun initUi()
}

interface PlayerActivityRouter
