package com.sary.sary.ui.main.categories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sary.sary.data.models.entity.Banner
import com.sary.sary.data.models.entity.Catalog
import com.sary.sary.data.models.remote.RemoteRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io
import kotlinx.coroutines.launch

class CategoriesViewModel(private val repository: RemoteRepository) : ViewModel() {

    /**
     * Banners
     */

    private var addBannersAPIMutableLiveData = MutableLiveData<List<Banner>>()
    val bannersAPILiveData: LiveData<List<Banner>> get() = addBannersAPIMutableLiveData

    fun fetchBanners() = viewModelScope.launch {
        repository.getAPIBanners().subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                { value -> println("Received: $value")  },
                { error -> println("Error: $error") },
                { println("Completed!") }
            )
    }


    /**
     * Catalogs
     */
    private fun fetchCatalogs(){
        viewModelScope.launch {
            repository.getAPICatalogs()
        }
    }

    private val catalogs: MutableLiveData<Observable<List<Catalog>>> by lazy {
        MutableLiveData<Observable<List<Catalog>>>().also {
            fetchCatalogs()
        }
    }

    fun getCatalogs(): LiveData<Observable<List<Catalog>>> {
        return catalogs
    }
}