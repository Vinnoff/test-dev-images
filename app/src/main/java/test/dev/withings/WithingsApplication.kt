package test.dev.withings

import android.app.Application
import org.koin.core.KoinApplication

class WithingsApplication : Application() {

    companion object {
        lateinit var koinApp: KoinApplication
    }
}