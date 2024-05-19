package android.template.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.template.data.SharedPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager
) : ViewModel() {

    private val _selectedUniversity = MutableStateFlow(sharedPreferencesManager.selectedUniversity)
    val selectedUniversity: StateFlow<String?> = _selectedUniversity

    private val _selectedDegree = MutableStateFlow(sharedPreferencesManager.selectedDegree)
    val selectedDegree: StateFlow<String?> = _selectedDegree
}
