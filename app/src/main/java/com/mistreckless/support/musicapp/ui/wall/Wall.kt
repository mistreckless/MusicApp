package com.mistreckless.support.musicapp.ui.wall

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.mistreckless.support.musicapp.R
import com.mistreckless.support.musicapp.domain.entity.Track
import com.mistreckless.support.musicapp.ui.BaseFragment
import com.mistreckless.support.musicapp.ui.Layout
import com.mistreckless.support.musicapp.ui.observeScroll
import kotlinx.android.synthetic.main.fragment_wall.*

/**
 * Created by @mistreckless on 22.10.2017. !
 */
@Layout(R.layout.fragment_wall)
class Wall : BaseFragment<WallPresenter>(), WallView {

    @ProvidePresenter
    fun providePresenter()=presenterProvider.get()
    @InjectPresenter
    lateinit var presenter : WallPresenter

    private val adapter by lazy { WallAdapter({ presenter.songClicked(it) }) }

    override fun initUi() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        presenter.controlList(recyclerView.observeScroll())
    }

    override fun showProgressBar(b: Boolean) {
        progressBar.visibility = if (b) View.VISIBLE else View.GONE
    }

    override fun setItemsToAdapter(items: List<Track>) {
        adapter.tracks.addAll(items)
    }

    override fun attachRouter() {
        presenter.attachRouter(activity)
    }

    override fun detachRouter() {
        presenter.detachRouter()
    }


}


interface WallView : MvpView {
    fun initUi()
    fun showProgressBar(b: Boolean)
    fun setItemsToAdapter(items: List<Track>)
}