package android.template.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.template.data.RepositoryImpl
import android.template.data.erasmulApi.models.ApiOpportunity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FeedViewModel (
        private val repo: RepositoryImpl
) : ViewModel() {


    private val _opportunitiesUiState : MutableStateFlow<List<ApiOpportunity>> =  MutableStateFlow(emptyList())
    val opportunitiesUiState: StateFlow<List<ApiOpportunity>> = _opportunitiesUiState

    init {
        fetchOpportunities()
    }

    private fun fetchOpportunities() {
        viewModelScope.launch {
            val opportunities = repo.getOpportunities()
            _opportunitiesUiState.update {
                opportunities
            }

        }
    }
}

