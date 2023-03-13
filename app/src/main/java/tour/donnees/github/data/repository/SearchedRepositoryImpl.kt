package tour.donnees.github.data.repository

import tour.donnees.github.data.api.API
import tour.donnees.github.data.dto.SearchedRepositoriesDTO

class SearchedRepositoryImpl(
    private val api: API
): SearchedRepository {

    override suspend fun getRepositoriesByPage(page: Int): SearchedRepositoriesDTO {
        return api.getRepositories(page = page)
    }
}