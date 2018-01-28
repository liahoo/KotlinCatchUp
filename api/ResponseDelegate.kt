package jp.liang.tanuki.web.api

/**
 * Outgoing by liang_hu on 西暦17/11/27.
 */
interface ResponseDelegate<in T> {
    fun onResponse(t: T?)
    fun onError(t:Throwable?)
}