package com.sary.sary.data.models.remote

import com.sary.sary.data.models.entity.BannerResult
import com.sary.sary.data.models.entity.CatalogResult
import io.reactivex.Observable
import retrofit2.http.GET

interface APIs {
    @GET("v2.5.1/baskets/76097/banners")
    fun fetchBanners(): Observable<BannerResult>

    @GET("baskets/76097/catalog")
    fun fetchCatalog(): Observable<CatalogResult>
}