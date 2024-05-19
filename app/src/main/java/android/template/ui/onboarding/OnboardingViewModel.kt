package android.template.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.template.data.SharedPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager
) : ViewModel() {

    private val _currentSlide = MutableStateFlow(0)
    val currentSlide: StateFlow<Int> = _currentSlide

    private val _selectedUniversity = MutableStateFlow(sharedPreferencesManager.selectedUniversity)
    val selectedUniversity: StateFlow<String?> = _selectedUniversity

    private val _selectedDegree = MutableStateFlow(sharedPreferencesManager.selectedDegree)
    val selectedDegree: StateFlow<String?> = _selectedDegree

    val isFirstLaunch: Boolean
        get() = sharedPreferencesManager.isFirstLaunch

    fun completeOnboarding() {
        viewModelScope.launch {
            sharedPreferencesManager.isFirstLaunch = false
        }
    }

    fun nextSlide() {
        _currentSlide.value += 1
    }

    fun setUniversity(university: String) {
        _selectedUniversity.value = university
        sharedPreferencesManager.selectedUniversity = university
    }

    fun setDegree(degree: String) {
        _selectedDegree.value = degree
        sharedPreferencesManager.selectedDegree = degree
    }
}
