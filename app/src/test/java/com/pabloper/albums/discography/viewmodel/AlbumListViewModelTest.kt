package com.pabloper.albums.discography.viewmodel

import com.pabloper.albums.discography.network.AlbumsService
import com.pabloper.albums.discography.network.model.AlbumNetwork
import com.pabloper.albums.util.FragmentUtil
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class AlbumListViewModelTest {

    @Mock
    private lateinit var fragmentUtil: FragmentUtil

    @Mock
    private lateinit var albumsService: AlbumsService

    private lateinit var albumListViewModel: AlbumListViewModel

    @Before
    fun initTest() {
        albumListViewModel = AlbumListViewModel(albumsService, fragmentUtil)
    }

    @Test
    fun albumListSuccess() {
        // Arrange
        Mockito.`when`(albumsService.getLatestAlbums()).thenReturn(Observable.just(getTestAlbums()))

        // Act
        val testObserver = albumListViewModel.getAlbums().test()

        // Assert
        testObserver.assertNoErrors()
        testObserver.assertValue { albums ->
            albums == getTestAlbums()
        }
    }

    @Test
    fun albumPressed() {
        // TODO need to setup RX test schedulers
 }


    private fun getTestAlbums(): List<AlbumNetwork> {
        val album1 = AlbumNetwork("album1", "1", "title1", "url1", "thumbnail1")
        val album2 = AlbumNetwork("album2", "2", "title2", "url2", "thumbnail2")
        return listOf(album1, album2)
    }
}