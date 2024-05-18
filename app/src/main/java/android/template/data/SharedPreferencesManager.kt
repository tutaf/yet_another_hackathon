package android.template.data

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "my_prefs"
        private const val KEY_IS_FIRST_LAUNCH = "is_first_launch"
        private const val KEY_SELECTED_UNIVERSITY = "selected_university"
        private const val KEY_SELECTED_DEGREE = "selected_degree"
    }

    var isFirstLaunch: Boolean
        get() = sharedPreferences.getBoolean(KEY_IS_FIRST_LAUNCH, true)
        set(value) = sharedPreferences.edit().putBoolean(KEY_IS_FIRST_LAUNCH, value).apply()

    var selectedUniversity: String?
        get() = sharedPreferences.getString(KEY_SELECTED_UNIVERSITY, null)
        set(value) = sharedPreferences.edit().putString(KEY_SELECTED_UNIVERSITY, value).apply()

    var selectedDegree: String?
        get() = sharedPreferences.getString(KEY_SELECTED_DEGREE, null)
        set(value) = sharedPreferences.edit().putString(KEY_SELECTED_DEGREE, value).apply()
}
