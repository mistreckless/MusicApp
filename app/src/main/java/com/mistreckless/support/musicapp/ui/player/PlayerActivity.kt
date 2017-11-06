package com.mistreckless.support.musicapp.ui.player

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Handler
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
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
import kotlinx.android.synthetic.main.activity_player.*


@Layout(R.layout.activity_player)
class PlayerActivity : BaseActivity<PlayerPresenter>(),PlayerActivityView,PlayerActivityRouter {

    @ProvidePresenter
    fun providePresenter()=presenterProvider.get()
    @InjectPresenter
    lateinit var playerPresenter : PlayerPresenter


    @SuppressLint("SetJavaScriptEnabled")
    override fun initUi() {
        val track = intent.getSerializableExtra("track") as Track
        val mainHandler = Handler()
        val bandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)

        val player = ExoPlayerFactory.newSimpleInstance(this, trackSelector)

        playerView.player=player
        val dataSourceFactory = DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "MusicApp"), bandwidthMeter)
        val extractorsFactory = DefaultExtractorsFactory()
        val audioSource = ExtractorMediaSource(Uri.parse(track.previewUrl),
                dataSourceFactory, extractorsFactory, null, null)
        player.prepare(audioSource)

    }

    override fun onDestroy() {
        playerView.player.stop()
        super.onDestroy()
    }

    override fun attachRouter() {
        playerPresenter.attachRouter(this)
    }

    override fun detachRouter() {
        playerPresenter.detachRouter()
    }
}

interface PlayerActivityView : MvpView{
    fun initUi()
}

interface PlayerActivityRouter
