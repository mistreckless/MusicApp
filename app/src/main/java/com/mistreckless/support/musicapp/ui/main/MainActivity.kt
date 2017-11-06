package com.mistreckless.support.musicapp.ui.main

import android.content.Intent
import android.view.View
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.mistreckless.support.musicapp.R
import com.mistreckless.support.musicapp.domain.entity.Track
import com.mistreckless.support.musicapp.ui.BaseActivity
import com.mistreckless.support.musicapp.ui.Layout
import com.mistreckless.support.musicapp.ui.player.PlayerActivity
import com.mistreckless.support.musicapp.ui.profile.Profile
import com.mistreckless.support.musicapp.ui.wall.Wall
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import kotlinx.android.synthetic.main.activity_main.*

@Layout(R.layout.activity_main)
class MainActivity : BaseActivity<MainActivityPresenter>(), MainActivityView, MainActivityRouter {


    @ProvidePresenter
    fun providePresenter() = presenterProvider.get()

    @InjectPresenter
    lateinit var presenter: MainActivityPresenter

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.activityResult(requestCode, resultCode, data)
    }

    override fun initUi() {
        bottomBar.visibility = View.VISIBLE
        bottomBar.selectTabWithId(R.id.tab_profile)
        bottomBar.setOnTabSelectListener { res ->
            when (res) {
                R.id.tab_music -> presenter.tabPlaylistClicked()
                R.id.tab_profile -> presenter.taProfileClicked()
            }
        }
    }

    override fun navigateToLoginActivity(request: AuthenticationRequest, requestCode: Int)
            = AuthenticationClient.openLoginActivity(this, requestCode, request)

    override fun navigateToWall() {
        supportFragmentManager.beginTransaction().replace(R.id.container, Wall()).commitAllowingStateLoss()
    }

    override fun navigateToProfile() {
        supportFragmentManager.beginTransaction().replace(R.id.container, Profile()).commitAllowingStateLoss()
    }

    override fun navigateToPlayer(track: Track) {
        startActivity(Intent(this, PlayerActivity::class.java).apply { putExtra("track", track) })
    }

    override fun attachRouter() {
        presenter.attachRouter(this)
    }

    override fun detachRouter() {
        presenter.detachRouter()
    }

}

interface MainActivityView : MvpView {
    fun initUi()
}

interface MainActivityRouter {
    fun navigateToLoginActivity(request: AuthenticationRequest, requestCode: Int)
    fun navigateToWall()
    fun navigateToProfile()
    fun navigateToPlayer(track: Track)
}
