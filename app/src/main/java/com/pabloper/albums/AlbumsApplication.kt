package com.pabloper.albums

import android.app.Application
import android.os.StrictMode
import com.pabloper.albums.dagger.component.AppComponent
import com.pabloper.albums.dagger.module.AppModule
import timber.log.Timber

class AlbumsApplication : Application() {

    private var appComponent: AppComponent = AppComponent.Initializer.init(AppModule(this))

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            initDebug()
        }
    }

    private fun initDebug() {
        Timber.plant(Timber.DebugTree())

        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )
        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }
}