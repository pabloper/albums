package com.pabloper.albums.discography.viewmodel

import com.pabloper.albums.base.viewmodel.ViewModel
import com.pabloper.albums.discography.network.AlbumsService
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class AlbumListViewModel @Inject constructor(
    private val albumsService: AlbumsService,
) : ViewModel() {


    override fun subscribe(subscriptions: CompositeDisposable) {
    }
}