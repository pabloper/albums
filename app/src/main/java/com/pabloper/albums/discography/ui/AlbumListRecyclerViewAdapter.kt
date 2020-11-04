package com.pabloper.albums.discography.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pabloper.albums.R
import com.pabloper.albums.discography.network.model.AlbumNetwork
import com.pabloper.albums.util.loadRemoteImage
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_album.view.*

class AlbumListRecyclerViewAdapter(private val albums: List<AlbumNetwork>) :
    RecyclerView.Adapter<AlbumListRecyclerViewAdapter.AlbumViewHolder>() {

    private val albumPressedStream = PublishSubject.create<AlbumNetwork>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(view)
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onBindViewHolder(
        holder: AlbumViewHolder,
        position: Int
    ) {
        holder.itemView.name_textView.text = albums[position].title
        holder.itemView.setOnClickListener { albumPressedStream.onNext(albums[position]) }

        loadRemoteImage(holder.itemView.album_imageView, albums[position].thumbnailUrl)
    }

    fun albumPressedStream(): Observable<AlbumNetwork> = albumPressedStream.hide()

    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}