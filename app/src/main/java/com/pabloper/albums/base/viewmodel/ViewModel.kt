package com.pabloper.albums.base.viewmodel

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class ViewModel {

    private var subscriptions: CompositeDisposable? = null

    fun bind() {
        unbind()
        subscriptions = CompositeDisposable()
        subscriptions?.let {
            subscribe(it)
        }
    }

    fun unbind() {
        subscriptions?.let {
            if (!it.isDisposed) {
                it.clear()
            }
            subscriptions = null
        }
    }

    protected fun addSubscription(subscription: Disposable) {
        subscriptions?.add(subscription)
    }

    protected abstract fun subscribe(subscriptions: CompositeDisposable)
}