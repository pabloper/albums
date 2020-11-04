package com.pabloper.albums.discography.viewmodel

import com.pabloper.albums.base.viewmodel.ViewModel
import com.pabloper.albums.discography.network.AlbumsService
import com.pabloper.albums.discography.network.model.AlbumNetwork
import com.pabloper.albums.discography.ui.AlbumDetailsFragment
import com.pabloper.albums.util.FragmentUtil
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

class AlbumListViewModel @Inject constructor(
    private val albumsService: AlbumsService,
    private val fragmentUtil: FragmentUtil
) : ViewModel() {

    private val selectedAlbum = PublishSubject.create<AlbumNetwork>()

    override fun subscribe(subscriptions: CompositeDisposable) {
        subscriptions.add(selectedAlbum.subscribe {
            onAlbumSelected(it)
        })
    }

    private fun onAlbumSelected(album: AlbumNetwork) {
        fragmentUtil.addFragmentDefault(
            AlbumDetailsFragment.newInstance(album.albumId),
            "AlbumDetailsFragment",
            true
        )
    }

    fun getAlbums(): Observable<List<AlbumNetwork>> =
        albumsService.getLatestAlbums()
            .subscribeOn(Schedulers.io())

    fun subscribeToAlbumPressed(albumStream: Observable<AlbumNetwork>) {
        albumStream.subscribe(selectedAlbum)
    }
}