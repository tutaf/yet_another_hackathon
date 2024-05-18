

package android.template.ui.mymodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import android.template.data.MyModelRepository
import android.template.ui.mymodel.PostModelState.Success
import android.template.ui.mymodel.PostModelState.Error
import android.template.ui.mymodel.PostModelState.Loading

import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val myModelRepository: MyModelRepository
) : ViewModel() {

    val uiState: StateFlow<PostModelState> = myModelRepository
        .myModels.map<List<String>, PostModelState>(::Success)
        .catch { emit(Error(it)) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    fun addMyModel(name: String) {
        viewModelScope.launch {
            myModelRepository.add(name)
        }
    }
}

sealed interface PostModelState {
    object Loading : PostModelState
    data class Error(val throwable: Throwable) : PostModelState
    data class Success(val data: List<String>) : PostModelState
}
