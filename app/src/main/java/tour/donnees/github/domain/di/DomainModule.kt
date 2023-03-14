package tour.donnees.github.domain.di

import org.koin.dsl.module
import tour.donnees.github.domain.usecase.GetRepositoryByPage
import tour.donnees.github.domain.usecase.GetRepositoryByPageImpl

val DomainModule = module {
    factory<GetRepositoryByPage> { GetRepositoryByPageImpl(get()) }
}