package com.sary.demo.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.concurrent.schedule

class SplashViewModel: ViewModel() {

    private val loading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>().also {
            loadingTask()
        }
    }

    fun getLoading(): LiveData<Boolean> {
        return loading
    }

    private fun loadingTask() {
        Timer().schedule(2000) {
            loading.postValue(true)
        }
    }
}

