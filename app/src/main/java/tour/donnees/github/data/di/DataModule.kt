package tour.donnees.github.data.di

import org.koin.dsl.module
import tour.donnees.github.data.repository.SearchedRepository
import tour.donnees.github.data.repository.SearchedRepositoryImpl

val DataModule = module {
    factory<SearchedRepository> { SearchedRepositoryImpl(get()) }
}