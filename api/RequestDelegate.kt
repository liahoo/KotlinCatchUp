package jp.liang.tanuki.web.api

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Outgoing by liang_hu on 西暦17/11/30.
 */
class RequestDelegate<out T>(private val observable: Observable<T>) {
    fun execute(delegate: ResponseDelegate<T>?): Disposable {
        val observer = SimpleApiObserver(delegate)
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(observer)
        return observer
    }
}
