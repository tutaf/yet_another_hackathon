package android.template.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.template.data.SharedPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager
) : ViewModel() {

    val isFirstLaunch: Boolean
        get() = sharedPreferencesManager.isFirstLaunch

    fun completeOnboarding() {
        viewModelScope.launch {
            sharedPreferencesManager.isFirstLaunch = false
        }
    }
}
