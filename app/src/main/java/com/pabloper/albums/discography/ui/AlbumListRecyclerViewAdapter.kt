package com.pabloper.albums.discography.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pabloper.albums.R
import com.pabloper.albums.discography.model.Album

class AlbumListRecyclerViewAdapter(private val albums: List<Album>) :
    RecyclerView.Adapter<AlbumListRecyclerViewAdapter.AlbumViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false)
        return AlbumViewHolder(view)
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onBindViewHolder(
        holder: AlbumViewHolder,
        position: Int
    ) {

    }


    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}