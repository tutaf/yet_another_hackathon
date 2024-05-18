

package android.template.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import android.template.data.local.database.MyModel
import android.template.data.local.database.MyModelDao
import javax.inject.Inject

interface Repository {
    val myModels: Flow<List<String>>

    suspend fun get(name: String)
}

class DefaultMyModelRepository @Inject constructor(
    private val myModelDao: MyModelDao
) : Repository {

    override val myModels: Flow<List<String>> =
        myModelDao.getMyModels().map { items -> items.map { it.name } }

    override suspend fun get(name: String) {
        myModelDao.insertMyModel(MyModel(name = name))
    }
}
