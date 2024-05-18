package android.template.data.erasmulApi.database

import android.template.data.models.ApiOpportunity
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

private val retrofit = Retrofit.Builder()
    .baseUrl("http://192.168.8.100:5000/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

val erasmusApiDB = retrofit.create(TheErasmusApiDB::class.java)

interface TheErasmusApiDB {
    @GET("retrieve")
    suspend fun getOpportunities(): Response<List<ApiOpportunity>>

    @POST("add")
    suspend fun addOpportunity(
        @Body opportunityRequest: ApiOpportunity
    ): Response<Unit>

}


