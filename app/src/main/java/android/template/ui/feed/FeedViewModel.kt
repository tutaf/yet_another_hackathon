package android.template.ui.feed

import android.template.data.OpportunityRepository
import android.template.data.models.ApiOpportunity
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(private val repository: OpportunityRepository) : ViewModel() {

    private val _opportunities = MutableLiveData<List<ApiOpportunity>>()
    val opportunities: LiveData<List<ApiOpportunity>> get() = _opportunities

    init {
        fetchOpportunities()
    }

    fun fetchOpportunities() {
        viewModelScope.launch {
            val result = repository.getOpportunities()
            _opportunities.postValue(result ?: emptyList())
        }
    }
}
