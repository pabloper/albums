package com.pabloper.albums.util

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.internal.functions.Functions
import timber.log.Timber

class RxBinderUtil(target: Any) {

    private var tag: String? = null
    private val compositeDisposable = CompositeDisposable()

    init {
        tag = target.javaClass.name
    }

    fun clear() {
        compositeDisposable.clear()
    }

    fun <U> bindProperty(
        observable: Observable<U>,
        setter: Consumer<U>
    ) {
        subscribeSetter(observable, setter, tag, compositeDisposable)
    }

    fun <U> bindProperty(
        observable: Observable<U>,
        setter: Consumer<U>,
        errorHandler: Consumer<Throwable?>
    ) {
        subscribeSetter(observable, setter, errorHandler, tag, compositeDisposable)
    }

    private fun <U> subscribeSetter(
        observable: Observable<U>,
        setter: Consumer<U>,
        tag: String?, compositeDisposable: CompositeDisposable
    ) {
        observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(SetterSubscriber(setter, tag, compositeDisposable))
    }

    private fun <U> subscribeSetter(
        observable: Observable<U>,
        setter: Consumer<U>,
        errorHandler: Consumer<Throwable?>,
        tag: String?, compositeDisposable: CompositeDisposable
    ) {
        observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(SetterSubscriber(setter, errorHandler, tag, compositeDisposable))
    }

    private class SetterSubscriber<U>(
        private val setter: Consumer<U>,
        private val errorHandler: Consumer<Throwable?>,
        private val completeHandler: Action,
        private val tag: String?,
        private val compositeDisposable: CompositeDisposable
    ) : Observer<U> {

        constructor(
            setter: Consumer<U>,
            tag: String?,
            compositeDisposable: CompositeDisposable
        ) : this(setter, Consumer { Timber.w(it) }, tag, compositeDisposable)

        constructor(
            setter: Consumer<U>,
            errorHandler: Consumer<Throwable?>,
            tag: String?,
            compositeDisposable: CompositeDisposable
        ) : this(setter, errorHandler, Functions.EMPTY_ACTION, tag, compositeDisposable)

        override fun onComplete() {
            Timber.tag(tag)
            Timber.v("onCompleted")
            try {
                completeHandler.run()
            } catch (e: Exception) {
                Timber.e(e)
            }
        }

        override fun onError(e: Throwable?) {
            Timber.tag(tag)
            try {
                Timber.e(e, "onError")
                errorHandler.accept(e)
            } catch (f: Exception) {
                Timber.e(f)
            }
        }

        override fun onSubscribe(d: Disposable) {
            compositeDisposable.add(d)
        }

        override fun onNext(u: U) {
            Timber.tag(tag)
            Timber.v("onNext %s", u)
            try {
                setter.accept(u)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }
}