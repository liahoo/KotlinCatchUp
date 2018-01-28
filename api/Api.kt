package jp.liang.tanuki.web.api

import io.reactivex.Observable
import jp.liang.tanuki.Flavor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Facilitate API calling to
 * //Api/User/Method?query
 * ->
 * Api.User.Method{ query() }.request(responseDelegate)
 */

object Api {
    /**
     * /version/check
     * アプリのバージョンチェックAPI
     *
     * e.g.
     * Api.version().check("appId")
     *              .observeOn(AndroidSchedulers.mainThread())
     *              .subscribeOn(Schedulers.newThread())
     *              .subscribe(onNext, onError)
     *
     * API仕様書 v1.0
     */
    fun version() = build { create(ApiVersion::class.java) }


    /**
     * /version/check
     * アプリのバージョンチェックAPI(簡易版)
     *
     * e.g.
     * Api.version{ check("appId") }.execute( ResponseDelegate<VersionResponse>(onResponse, onFailed) )
     *
     * API仕様書 v1.0
     */
    fun <T> version(apiMethod: ApiVersion.() -> Observable<T>):RequestDelegate<T> = RequestDelegate(version().apiMethod())

    private fun <T> build(service: Retrofit.() -> T)  =
            Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Flavor.apiServerUrl)
                    .build()
                    .service()
}
