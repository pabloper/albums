package com.pabloper.albums.discography.ui

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.pabloper.albums.R
import com.pabloper.albums.base.ui.InjectActivity
import com.pabloper.albums.base.ui.MvvmFragment
import com.pabloper.albums.base.viewmodel.ViewModel
import com.pabloper.albums.dagger.component.fragment.AlbumDetailsFragmentComponent
import com.pabloper.albums.dagger.module.fragment.AlbumDetailsFragmentModule
import com.pabloper.albums.discography.network.model.AlbumNetwork
import com.pabloper.albums.discography.viewmodel.AlbumDetailsViewModel
import com.pabloper.albums.util.RxBinderUtil
import com.pabloper.albums.util.loadRemoteImage
import javax.inject.Inject

class AlbumDetailsFragment : MvvmFragment<AlbumDetailsViewModel, AlbumDetailsFragmentComponent>() {

    private lateinit var progressBar: ProgressBar
    private lateinit var imageView: ImageView
    private var albumId: String = ""

    @Inject
    lateinit var albumDetailsViewModel: AlbumDetailsViewModel

    companion object {

        @JvmStatic
        fun newInstance(albumId: String): AlbumDetailsFragment {
            val args = Bundle()
            args.putString(
                ALBUM_ID,
                albumId
            )
            val fragment = AlbumDetailsFragment()
            fragment.arguments = args
            return fragment
        }

        private const val ALBUM_ID = "ALBUM_ID"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_album_details, container, false)
        initView(root)
        return root
    }

    private fun initView(view: View) {
        albumId = arguments!!.getString(ALBUM_ID, "")
        imageView = view.findViewById(R.id.album_imageView)
        progressBar = view.findViewById(R.id.loading_progressBar)
    }

    override fun onStart() {
        super.onStart()
        showLoadingBar(true)
    }

    private fun albumReceived(albums: AlbumNetwork) {
        loadRemoteImage(imageView, albums.url)
        showLoadingBar(false)
    }

    private fun showNetworkErrorToast() {
        val toast =
            Toast.makeText(context, resources.getString(R.string.network_error), Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    private fun showLoadingBar(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun bindProperties(rxBinderUtil: RxBinderUtil) {
        rxBinderUtil.bindProperty(albumDetailsViewModel.getAlbum(), {
            albumReceived(it)
        }, {
            showLoadingBar(false)
            showNetworkErrorToast()
        })
    }

    override fun inject() {
        component().inject(this)
    }

    override fun createComponent(): AlbumDetailsFragmentComponent {
        return (activity as InjectActivity)
            .getActivityComponent()
            .plusAlbumDetailsComponent(AlbumDetailsFragmentModule(albumId))
    }

    override fun getViewModel(): ViewModel {
        return albumDetailsViewModel
    }
}
