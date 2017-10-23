package com.mistreckless.support.musicapp.ui.main

import android.content.Intent
import android.view.View
import com.mistreckless.support.musicapp.R
import com.mistreckless.support.musicapp.ui.BaseActivity
import com.mistreckless.support.musicapp.ui.Layout
import com.mistreckless.support.musicapp.ui.profile.Profile
import com.mistreckless.support.musicapp.ui.wall.Wall
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import kotlinx.android.synthetic.main.activity_main.*

@Layout(R.layout.activity_main)
class MainActivity : BaseActivity<MainActivityPresenter, MainPresenterProviderFactory>(), MainActivityView, MainActivityRouter {

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
            = presenter.activityResult(requestCode, resultCode, data)

    override fun initUi() {
        bottomBar.visibility= View.VISIBLE
        bottomBar.selectTabWithId(R.id.tab_profile)
        bottomBar.setOnTabSelectListener { res->
            when(res){
                R.id.tab_music ->presenter.tabPlaylistClicked()
                R.id.tab_profile->presenter.taProfileClicked()
            }
        }
    }

    override fun navigateToLoginActivity(request: AuthenticationRequest, requestCode: Int)
            = AuthenticationClient.openLoginActivity(this, requestCode, request)

    override fun navigateToWall() {
        supportFragmentManager.beginTransaction().replace(R.id.container,Wall()).commitAllowingStateLoss()
    }

    override fun navigateToProfile() {
        supportFragmentManager.beginTransaction().replace(R.id.container,Profile()).commitAllowingStateLoss()
    }

//    private fun initPlayer(response: AuthenticationResponse) {
//        val playerConfig = Config(this,response.accessToken, CLIENT_ID)
//        Spotify.getPlayer(playerConfig,this,object :SpotifyPlayer.InitializationObserver{
//            override fun onInitialized(p0: SpotifyPlayer?) {
//                spotifyPlayer=p0
//                spotifyPlayer?.addConnectionStateCallback(this@MainActivity)
//                spotifyPlayer?.addNotificationCallback(this@MainActivity)
//            }
//
//            override fun onError(p0: Throwable?) {
//                Log.e("initplayer",p0?.message)
//            }
//
//        })
//    }

}

interface MainActivityView {
    fun initUi()
}

interface MainActivityRouter {
    fun navigateToLoginActivity(request: AuthenticationRequest, requestCode: Int)
    fun navigateToWall()
    fun navigateToProfile()
}
