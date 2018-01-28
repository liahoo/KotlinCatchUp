package jp.liang.tanuki.web.api

import io.reactivex.Observable
import jp.liang.tanuki.web.response.VersionResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by liang_hu on 西暦17/11/22.
 */
interface ApiVersion {
    @GET("/version/check")
    fun check(@Query("key") key: String): Observable<VersionResponse>

    @GET("/version/config")
    fun check(): Observable<VersionResponse>
}