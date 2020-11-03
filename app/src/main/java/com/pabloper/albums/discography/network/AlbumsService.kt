package com.pabloper.albums.discography.network

import com.pabloper.albums.discography.network.model.AlbumNetwork
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface AlbumsService {

    @GET("albums")
    fun getLatestAlbums(): Observable<List<AlbumNetwork>>
}