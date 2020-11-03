package com.pabloper.albums.base.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.pabloper.albums.util.RxBinderUtil
import com.pabloper.albums.base.viewmodel.ViewModel

abstract class MvvmFragment<T : ViewModel?, U> : Fragment() {
    private var component: U? = null
    private val rxBinderUtil: RxBinderUtil = RxBinderUtil(this)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        inject()
    }

    override fun onStart() {
        super.onStart()
        bindViewModel()
        bindProperties(rxBinderUtil)
    }

    protected fun bindViewModel() {
        getViewModel().bind()
    }

    override fun onStop() {
        super.onStop()
        rxBinderUtil.clear()
        getViewModel().unbind()
    }

    fun component(): U {
        return component ?: createComponent()
    }

    protected abstract fun getViewModel(): ViewModel

    protected abstract fun createComponent(): U

    protected abstract fun inject()
    protected abstract fun bindProperties(rxBinderUtil: RxBinderUtil)
}
