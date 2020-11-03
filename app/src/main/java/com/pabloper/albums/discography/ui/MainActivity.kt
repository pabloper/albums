package com.pabloper.albums.discography.ui

import android.os.Bundle
import com.pabloper.albums.AlbumsApplication
import com.pabloper.albums.R
import com.pabloper.albums.base.ui.InjectActivity
import com.pabloper.albums.dagger.component.ActivityComponent
import com.pabloper.albums.dagger.module.ActivityModule
import com.pabloper.albums.util.FragmentUtil
import javax.inject.Inject

class MainActivity : InjectActivity() {
    private lateinit var activityComponent: ActivityComponent

    @Inject
    lateinit var fragmentUtil: FragmentUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentUtil.addFragment(
            R.id.fragment, AlbumListFragment(),
            "AlbumListFragment", true
        )
    }

    override fun setupInjection() {
        activityComponent = (application as AlbumsApplication).getAppComponent()
            .plusActivity(ActivityModule(this))
        activityComponent.inject(this)
    }

    override fun getActivityComponent(): ActivityComponent {
        return activityComponent
    }
}