package com.sary.demo.ui.main.categories

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sary.demo.data.models.entity.BannerResult
import com.sary.demo.data.models.entity.CatalogResult
import com.sary.demo.data.models.remote.RemoteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io
import kotlinx.coroutines.launch

class CategoriesViewModel(private val repository: RemoteRepository) : ViewModel() {

    /**
     * Banners
     */

    private var addBannersAPIMutableLiveData = MutableLiveData<BannerResult>()
    val bannersAPILiveData: LiveData<BannerResult> get() = addBannersAPIMutableLiveData

    fun fetchBanners() = viewModelScope.launch {
        repository.getAPIBanners().subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                { value -> addBannersAPIMutableLiveData.postValue(value)  },
                { error -> println("Error: $error") },
                { println("Completed!") }
            )
    }


    /**
     * Catalogs
     */
    private var addCatalogsAPIMutableLiveData = MutableLiveData<CatalogResult>()
    val catalogAPILiveData: LiveData<CatalogResult> get() = addCatalogsAPIMutableLiveData

    @SuppressLint("CheckResult")
    fun fetchCatalogs() = viewModelScope.launch {
        repository
            .getAPICatalogs()
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> addCatalogsAPIMutableLiveData.postValue(value) },
                { error -> println("Error: $error") },
                { println("Completed!") }
            )


    }
}

