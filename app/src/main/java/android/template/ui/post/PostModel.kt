

package android.template.ui.post


import android.template.data.OpportunityRepository
import android.template.data.models.ApiOpportunity
import android.template.ui.mymodel.PostScreenArgCategory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: OpportunityRepository,
    savedStateHandle: SavedStateHandle,

    ) : ViewModel() {

    private val oppId = requireNotNull(savedStateHandle.get<String>(PostScreenArgCategory))


    private val _opportunities = MutableLiveData<ApiOpportunity>()
    val opportunities: LiveData<ApiOpportunity> get() = _opportunities

    init {
        fetchOpportunities()
    }

    fun fetchOpportunities() {
        viewModelScope.launch {
            val result = repository.getOpportunities()
            val specificOpportunity = result?.find { it.id.toString() == oppId }
            _opportunities.postValue(specificOpportunity?.let { it } ?: notFound)

        }
    }
}



val notFound: ApiOpportunity = ApiOpportunity(
    id = -1, // or any value that doesn't correspond to a valid ID
    duration = 0,
    title = "Nothing Found",
    degree = "",
    thumbnailLink = "",
    country = "",
    category = "",
    content = "",
    deadline = "",
    requirements = emptyList()
)


