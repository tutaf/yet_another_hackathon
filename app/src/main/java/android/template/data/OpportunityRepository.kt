package android.template.data

import android.template.data.models.ApiOpportunity
import android.template.data.network.RemoteDataSource
import android.util.Log
import javax.inject.Inject

class OpportunityRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    suspend fun getOpportunities(): List<ApiOpportunity>? {
        val response = remoteDataSource.getOpportunities()
        return if (response.isSuccessful) {
            val opportunities = response.body()?.opportunities
            Log.d("OpportunityRepository", "Received opportunities: $opportunities")
            opportunities
        } else {
            val errorBody = response.errorBody()?.string()
            Log.e("OpportunityRepository", "Failed to fetch opportunities: ${response.code()} - $errorBody")
            null
        }
    }
}
