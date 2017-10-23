package com.mistreckless.support.musicapp.ui.player

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
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

        webView.settings.javaScriptEnabled=true
        webView.settings.useWideViewPort=true
        webView.settings.loadWithOverviewMode=true
        webView.settings.builtInZoomControls=true
        webView.isSoundEffectsEnabled=true
        webView.webChromeClient= WebChromeClient()

        webView.loadUrl(track.previewUrl)
    }

}

interface PlayerActivityView {
    fun initUi()
}

interface PlayerActivityRouter
