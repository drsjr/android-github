package tour.donnees.github

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import tour.donnees.github.config.MockNetwork
import tour.donnees.github.config.MockRepository
import tour.donnees.github.config.MockUseCase
import tour.donnees.github.config.MockViewModel

class TestApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TestApplication)
            modules(
                listOf(
                    MockNetwork, MockRepository, MockUseCase, MockViewModel
                )
            )
        }
    }
}