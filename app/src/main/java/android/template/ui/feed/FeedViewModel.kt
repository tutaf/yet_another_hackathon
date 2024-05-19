package android.template.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import android.template.data.RepositoryImpl
import android.template.data.models.ApiOpportunity
import android.template.ui.feed.FeedUiState.Error
import android.template.ui.feed.FeedUiState.Loading
import android.template.ui.feed.FeedUiState.Success
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
        private val repo: RepositoryImpl
) : ViewModel() {

    val uiState: StateFlow<FeedUiState> = repo
            .myModels.map<List<String>, FeedUiState>(::Success)
        .catch { emit(Error(it)) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    fun addMyModel(name: String) {
        viewModelScope.launch {
            repo.get(name)

        }
    }

    private val _opportunitiesUiState : MutableStateFlow<List<ApiOpportunity>> =  MutableStateFlow(emptyList())
    val opportunitiesUiState: StateFlow<List<ApiOpportunity>> = _opportunitiesUiState

    init {
        fetchOpportunities()
    }

    private fun fetchOpportunities() {
        viewModelScope.launch {
            val opportunities = repo.getOpportunities()
            _opportunitiesUiState.value = opportunities

        }
    }
}

sealed interface FeedUiState {
    object Loading : FeedUiState
    data class Error(val throwable: Throwable) : FeedUiState
    data class Success(val data: List<String>) : FeedUiState
    data class Oportunities(val data: List<ApiOpportunity>) : FeedUiState



}