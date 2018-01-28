package jp.liang.tanuki.web.api

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import jp.liang.tanuki.log

/**
 * Created by liang_hu on 西暦17/11/22.
 */
class SimpleApiObserver<T>(val delegate: ResponseDelegate<T>?): Observer<T>, Disposable {
    private var disposable: Disposable? = null

    override fun isDisposed(): Boolean = disposable?.isDisposed ?: true

    override fun dispose() {
        disposable?.dispose()
    }

    override fun onSubscribe(d: Disposable?) {
        log("[onSubscribe]")
        disposable = d
    }

    override fun onComplete() {
        log("[onComplete]")

    }

    override fun onNext(t: T?) {
        log("[onNext] $t")
        delegate?.onResponse(t)
    }

    override fun onError(t: Throwable?) {
        t?.printStackTrace()
        delegate?.onError(t)
    }
}