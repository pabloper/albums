package com.pabloper.albums.dagger.module

import android.content.Context
import com.pabloper.albums.dagger.Qualifiers
import com.pabloper.albums.base.ui.InjectActivity
import com.pabloper.albums.util.FragmentUtil
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: InjectActivity) {

    @Qualifiers.ForActivity
    @Provides
    fun provideActivityContext(): Context {
        return activity
    }

    @Provides
    fun provideFragmentUtil(): FragmentUtil {
        return FragmentUtil(activity)
    }

}