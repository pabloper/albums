package com.pabloper.albums.dagger.module.fragment

import com.pabloper.albums.dagger.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class AlbumDetailsFragmentModule(private val albumId: String) {

    @Provides
    @FragmentScope
    fun provideAlbumId(): String {
        return albumId
    }
}