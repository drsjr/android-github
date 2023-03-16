package tour.donnees.github.config

import org.koin.dsl.module
import tour.donnees.github.domain.model.RepositoryModel
import tour.donnees.github.domain.usecase.GetRepositoryByPage
import tour.donnees.github.domain.usecase.GetRepositoryByPageImpl

val MockModule  = module {
    factory<GetRepositoryByPage> { MockGetRepositoryByPageImpl() }
}


class MockGetRepositoryByPageImpl: GetRepositoryByPage {
    override suspend fun getRepositoryByPage(page: Int): Collection<RepositoryModel> {
        return emptyList()
    }

}