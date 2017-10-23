package com.mistreckless.support.musicapp.ui.profile

import android.view.View
import com.mistreckless.support.musicapp.R
import com.mistreckless.support.musicapp.ui.BaseFragment
import com.mistreckless.support.musicapp.ui.Layout
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * Created by @mistreckless on 23.10.2017. !
 */

@Layout(R.layout.fragment_profile)
class Profile : BaseFragment<ProfilePresenter,ProfilePresenterProviderFactory>(), ProfileView{
    override fun showLoading(b: Boolean) {
        progressBar.visibility=if (b) View.VISIBLE else View.GONE
    }

    override fun initUi(id: String) {
        toolbar.title=id
    }

}


interface ProfileView {
    fun initUi(id: String)
    fun showLoading(b: Boolean)
}