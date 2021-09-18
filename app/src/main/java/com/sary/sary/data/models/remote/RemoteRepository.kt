package com.sary.sary.data.models.remote

import com.sary.sary.data.models.entity.Banner
import com.sary.sary.data.models.entity.Catalog
import io.reactivex.Observable

interface RemoteRepository {
    suspend fun getAPIBanners(): Observable<List<Banner>>
    suspend fun getAPICatalogs(): Observable<List<Catalog>>
}