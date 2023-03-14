package tour.donnees.github.domain.usecase

import tour.donnees.github.data.repository.SearchedRepository
import tour.donnees.github.domain.mapping.RepositoryMapping
import tour.donnees.github.domain.model.RepositoryModel

class GetRepositoryByPageImpl(
    private val repository: SearchedRepository
): GetRepositoryByPage {
    override suspend fun getRepositoryByPage(page: Int): Collection<RepositoryModel> {
        val mapping = RepositoryMapping()
        return repository.getRepositoriesByPage(page).items?.map {
            mapping.mapToRepository(it)
        } ?: emptyList()
    }
}