

package android.template.data

import android.template.data.erasmulApi.DataModuleImpl
import android.template.data.erasmulApi.models.ApiOpportunity
import android.util.Log

interface Repository {
    suspend fun getOpportunities(): List<ApiOpportunity>

}

class RepositoryImpl (
    private val dataModule: DataModuleImpl
) : Repository {

    override suspend fun getOpportunities(): List<ApiOpportunity> {
        Log.e(">>>>>>repository goood", "joijob")
        return dataModule.getOpportunities().body
    }
}
