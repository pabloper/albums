package com.pabloper.albums.dagger.component

import com.pabloper.albums.dagger.FragmentScope
import com.pabloper.albums.discography.ui.AlbumListFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [])
interface FragmentComponent {

    fun inject(albumListFragment: AlbumListFragment)
}