
package android.template

import android.app.Application
import android.template.data.di.appModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.core.context.startKoin

@HiltAndroidApp
class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}
