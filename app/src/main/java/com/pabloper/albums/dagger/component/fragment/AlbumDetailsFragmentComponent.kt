package com.pabloper.albums.dagger.component.fragment

import com.pabloper.albums.dagger.FragmentScope
import com.pabloper.albums.dagger.module.fragment.AlbumDetailsFragmentModule
import com.pabloper.albums.discography.ui.AlbumDetailsFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [AlbumDetailsFragmentModule::class])
interface AlbumDetailsFragmentComponent {
    fun inject(albumDetailsFragment: AlbumDetailsFragment)
}