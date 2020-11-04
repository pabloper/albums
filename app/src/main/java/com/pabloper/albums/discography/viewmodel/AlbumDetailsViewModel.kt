package com.pabloper.albums.discography.viewmodel

import com.pabloper.albums.base.viewmodel.ViewModel
import com.pabloper.albums.discography.network.AlbumsService
import com.pabloper.albums.discography.network.model.AlbumNetwork
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AlbumDetailsViewModel @Inject constructor(
    private val albumsService: AlbumsService,
    private val albumId: String
) : ViewModel() {


    override fun subscribe(subscriptions: CompositeDisposable) {
        // Not used
    }

    fun getAlbum(): Observable<AlbumNetwork> =
        albumsService.getAlbumById(albumId)
            .subscribeOn(Schedulers.io())

}