package com.pabloper.albums.dagger.module

import android.content.Context
import com.pabloper.albums.AlbumsApplication
import com.pabloper.albums.dagger.Qualifiers
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: AlbumsApplication) {

    @Qualifiers.ForApplication
    @Provides
    fun provideApplicationContext(): Context {
        return application
    }
}