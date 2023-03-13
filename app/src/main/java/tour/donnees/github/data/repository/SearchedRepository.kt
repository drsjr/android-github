package tour.donnees.github.data.repository

import tour.donnees.github.data.dto.SearchedRepositoriesDTO

interface SearchedRepository {

    suspend fun getRepositoriesByPage(page: Int): SearchedRepositoriesDTO
}