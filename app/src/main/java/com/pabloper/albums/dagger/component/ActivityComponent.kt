package com.pabloper.albums.dagger.component


import com.pabloper.albums.discography.ui.MainActivity
import com.pabloper.albums.dagger.ActivityScope
import com.pabloper.albums.dagger.module.ActivityModule
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    fun plusFragment(): FragmentComponent

}