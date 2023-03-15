package tour.donnees.github.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import tour.donnees.github.presentation.viewmodel.MainViewModel

val PresentationModule = module {
    viewModelOf(::MainViewModel)
}