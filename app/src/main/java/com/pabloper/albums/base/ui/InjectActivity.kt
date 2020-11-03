package com.pabloper.albums.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pabloper.albums.AlbumsApplication
import com.pabloper.albums.dagger.component.ActivityComponent
import com.pabloper.albums.dagger.component.AppComponent

abstract class InjectActivity : AppCompatActivity() {

    protected val appComponent: AppComponent
        get() = (getApplication() as AlbumsApplication).getAppComponent()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupInjection()
    }

    protected abstract fun setupInjection()

    abstract fun getActivityComponent(): ActivityComponent
}