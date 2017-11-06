package com.mistreckless.support.musicapp.ui.profile

import android.view.View
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.mistreckless.support.musicapp.R
import com.mistreckless.support.musicapp.ui.BaseFragment
import com.mistreckless.support.musicapp.ui.Layout
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * Created by @mistreckless on 23.10.2017. !
 */

@Layout(R.layout.fragment_profile)
class Profile : BaseFragment<ProfilePresenter>(), ProfileView{

    @ProvidePresenter
    fun providePresenter() = presenterProvider.get()
    @InjectPresenter
    lateinit var presenter : ProfilePresenter

    override fun showLoading(b: Boolean) {
        progressBar.visibility=if (b) View.VISIBLE else View.GONE
    }

    override fun initUi(id: String) {
        toolbar.title=id
    }

    override fun attachRouter() {
        presenter.attachRouter(activity)
    }

    override fun detachRouter() {
        presenter.detachRouter()
    }
}


interface ProfileView : MvpView {
    fun initUi(id: String)
    fun showLoading(b: Boolean)
}