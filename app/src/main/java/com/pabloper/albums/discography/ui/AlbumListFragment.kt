package com.pabloper.albums.discography.ui

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pabloper.albums.R
import com.pabloper.albums.base.ui.InjectActivity
import com.pabloper.albums.base.ui.MvvmFragment
import com.pabloper.albums.base.viewmodel.ViewModel
import com.pabloper.albums.dagger.component.FragmentComponent
import com.pabloper.albums.discography.network.model.AlbumNetwork
import com.pabloper.albums.discography.viewmodel.AlbumListViewModel
import com.pabloper.albums.util.RxBinderUtil
import javax.inject.Inject

class AlbumListFragment : MvvmFragment<AlbumListViewModel, FragmentComponent>() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: AlbumListRecyclerViewAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var progressBar: ProgressBar

    @Inject
    lateinit var albumListViewModel: AlbumListViewModel

    companion object {

        @JvmStatic
        fun newInstance(): AlbumListFragment {
            return AlbumListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_album_list, container, false)
        initView(root)
        return root
    }

    private fun initView(view: View) {
        viewManager = LinearLayoutManager(this.context)
        recyclerView = view.findViewById(R.id.album_recycler_view)
        progressBar = view.findViewById(R.id.loading_progressBar)
    }

    override fun onStart() {
        super.onStart()
        showLoadingBar(true)
    }


    private fun albumListReceived(albums: List<AlbumNetwork>) {
        viewAdapter = AlbumListRecyclerViewAdapter(albums)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        albumListViewModel.subscribeToAlbumPressed(viewAdapter.albumPressedStream())
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
        rxBinderUtil.bindProperty(albumListViewModel.getAlbums(), {
            albumListReceived(it)
        }, {
            showLoadingBar(false)
            showNetworkErrorToast()
        })
    }

    override fun inject() {
        component().inject(this)
    }

    override fun createComponent(): FragmentComponent {
        return (activity as InjectActivity).getActivityComponent().plusFragment()
    }

    override fun getViewModel(): ViewModel {
        return albumListViewModel
    }
}