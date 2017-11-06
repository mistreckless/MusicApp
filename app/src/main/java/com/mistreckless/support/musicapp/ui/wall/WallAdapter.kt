package com.mistreckless.support.musicapp.ui.wall

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mistreckless.support.musicapp.R
import kotlinx.android.synthetic.main.track_item.*
import com.mistreckless.support.musicapp.domain.entity.Track
import kotlinx.android.extensions.LayoutContainer

/**
 * Created by mistreckless on 23.10.17.
 */
class WallAdapter (private val songClicked : (track : Track) -> Unit): RecyclerView.Adapter<TrackViewHolder>() {

    val tracks: MutableList<Track> by lazy { mutableListOf<Track>() }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = TrackViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.track_item, parent, false))

    override fun onBindViewHolder(holder: TrackViewHolder?, position: Int) {
        val track = tracks[position]
        holder?.txtName?.text=track.id
        holder?.txtSong?.text=track.name
        holder?.rootView?.setOnClickListener { songClicked(track) }
    }

    override fun getItemCount() = tracks.size
}

class TrackViewHolder(itemView: View?, override val containerView: View? = itemView) : RecyclerView.ViewHolder(itemView), LayoutContainer