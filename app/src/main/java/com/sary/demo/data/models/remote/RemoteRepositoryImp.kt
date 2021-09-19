package com.sary.demo.data.models.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteRepositoryImp(private val api: APIs): RemoteRepository {
    override suspend fun getAPIBanners() = withContext(Dispatchers.IO) {
        api.fetchBanners()
    }

    override suspend fun getAPICatalogs() = withContext(Dispatchers.IO) {
        api.fetchCatalog()
    }
}