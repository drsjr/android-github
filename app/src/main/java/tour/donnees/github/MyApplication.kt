package tour.donnees.github

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module
import tour.donnees.github.data.di.DataModule
import tour.donnees.github.data.di.NetworkModule
import tour.donnees.github.domain.di.DomainModule

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApplication)
            modules(getModules())
        }
    }

    private fun getModules(): List<Module> {
        return listOf(
            NetworkModule,
            DataModule,
            DomainModule
        )
    }
}