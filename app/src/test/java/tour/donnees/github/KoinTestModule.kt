package tour.donnees.github

import org.junit.Test
import org.koin.test.check.checkModules
import tour.donnees.github.data.di.DataModule
import tour.donnees.github.data.di.NetworkModule
import tour.donnees.github.domain.di.DomainModule

class KoinTestModule {
    @Test
    fun test() {
       checkModules {
           modules(DataModule, NetworkModule, DomainModule)
       }
    }
}