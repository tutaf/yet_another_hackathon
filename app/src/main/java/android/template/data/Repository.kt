

package android.template.data

import android.template.data.erasmulApi.database.TheErasmusApiDB
import android.template.data.erasmulApi.di.DataModule
import android.template.data.erasmulApi.di.DataModuleImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import android.template.data.local.database.MyModel
import android.template.data.local.database.MyModelDao
import android.template.data.models.ApiOpportunity
import android.util.Log
import javax.inject.Inject

interface Repository {
    val myModels: Flow<List<String>>

    suspend fun get(name: String)
    suspend fun getOpportunities(): List<ApiOpportunity>

}

class RepositoryImpl @Inject constructor(
    private val myModelDao: MyModelDao,
    private val dataModule: DataModuleImpl
) : Repository {

    override val myModels: Flow<List<String>> =
        myModelDao.getMyModels().map { items -> items.map { it.name } }

    override suspend fun get(name: String) {
        myModelDao.insertMyModel(MyModel(name = name))
    }

    override suspend fun getOpportunities(): List<ApiOpportunity> {
        Log.e(">>>>>>repository goood", "joijob")
        return dataModule.getOpportunities()
    }
}
