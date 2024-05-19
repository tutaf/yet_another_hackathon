package android.template.data.network

import android.template.data.models.OpportunitiesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("retrieve")
    suspend fun getOpportunities(): Response<OpportunitiesResponse>
}
