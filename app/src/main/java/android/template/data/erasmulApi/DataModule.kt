
package android.template.data.erasmulApi

import android.template.data.erasmulApi.database.TheErasmusApiDB
import android.template.data.erasmulApi.database.erasmusApiDB
import android.template.data.erasmulApi.models.ApiOpportunities
import android.template.data.erasmulApi.models.ApiOpportunity
import android.util.Log

interface DataModule {
    suspend fun getOpportunities(): ApiOpportunities
    suspend fun addOpportunity(opportunity: ApiOpportunity)

}


class DataModuleImpl : DataModule {
    private val erasmusApiDb: TheErasmusApiDB = erasmusApiDB

    override suspend fun getOpportunities(): ApiOpportunities {
        val response = erasmusApiDb.getOpportunities()
        val responseBody = response.body()

        val opportunities = if (response.isSuccessful && responseBody != null) {
            Log.e(">>>>>>datamodule goood", responseBody.toString())
            responseBody

        } else {
            Log.e(">>>>>>error on response", "getOpportunities is either unsuccessful or null")
            ApiOpportunities(emptyList())
        }

        return opportunities
    }

    override suspend fun addOpportunity(opportunity: ApiOpportunity) {
        TODO("Not yet implemented")
    }


}


val apiOpportunity = ApiOpportunity(
    id = 1,
    duration = 60, // duration in minutes
    title = "Sample Opportunity",
    degree = "Bachelor's",
    thumbnailLink = "https://example.com/thumbnail.jpg",
    country = "Sample Country",
    category = "Sample Category",
    content = "Sample opportunity content",
    deadline = "2024-12-31", // deadline in YYYY-MM-DD format
    requirements = listOf("Requirement 1", "Requirement 2", "Requirement 3")
)
