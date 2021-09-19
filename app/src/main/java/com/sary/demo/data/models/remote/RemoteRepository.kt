package com.sary.demo.data.models.remote

import com.sary.demo.data.models.entity.BannerResult
import com.sary.demo.data.models.entity.CatalogResult
import io.reactivex.Observable

interface RemoteRepository {
    suspend fun getAPIBanners(): Observable<BannerResult>
    suspend fun getAPICatalogs(): Observable<CatalogResult>
}