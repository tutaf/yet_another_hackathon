package android.template.data.network

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getOpportunities() = apiService.getOpportunities()
}
