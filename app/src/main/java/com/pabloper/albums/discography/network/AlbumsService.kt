package com.pabloper.albums.discography.network

import com.pabloper.albums.discography.network.model.AlbumNetwork
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumsService {

    @GET("/photos")
    fun getLatestAlbums(): Observable<List<AlbumNetwork>>

    @GET("/photos/{id}")
    fun getAlbumById(@Path("id") id: String): Observable<AlbumNetwork>
}