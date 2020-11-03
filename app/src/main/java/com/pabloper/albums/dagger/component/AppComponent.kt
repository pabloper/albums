package com.pabloper.albums.dagger.component

import com.pabloper.albums.AlbumsApplication
import com.pabloper.albums.dagger.module.ActivityModule
import com.pabloper.albums.dagger.module.AppModule
import com.pabloper.albums.dagger.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(templateApplication: AlbumsApplication)

    fun plusActivity(activityModule: ActivityModule): ActivityComponent

    object Initializer {
        @Synchronized
        fun init(appModule: AppModule): AppComponent {
            return DaggerAppComponent.builder()
                .appModule(appModule)
                .build()
        }
    }
}