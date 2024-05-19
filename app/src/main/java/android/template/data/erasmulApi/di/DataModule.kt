
package android.template.data.erasmulApi.di

import android.template.data.erasmulApi.database.TheErasmusApiDB
import android.template.data.erasmulApi.database.erasmusApiDB
import android.template.data.models.ApiOpportunity
import android.util.Log
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

interface DataModule {
    suspend fun getOpportunities(): List<ApiOpportunity>
    suspend fun addOpportunity(opportunity: ApiOpportunity)

}

@Module
@InstallIn(SingletonComponent::class)
interface DataModuleBinder {
    @Binds
    fun bindDataModule(dataModuleImpl: DataModuleImpl): DataModule
}

@Singleton
class DataModuleImpl @Inject constructor() : DataModule {
    private val erasmusApiDb: TheErasmusApiDB = erasmusApiDB

    override suspend fun getOpportunities(): List<ApiOpportunity> {
        val response = erasmusApiDb.getOpportunities()
        val responseBody = response.body()

        val opportunities: List<ApiOpportunity> = if (response.isSuccessful && responseBody != null) {
            Log.e(">>>>>>datamodule goood", responseBody.toString())
            responseBody.body

        } else {
            Log.e(">>>>>>error on response", "getOpportunities is either unsuccessful or null")
            listOf(apiOpportunity)
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
