package tour.donnees.github.domain.usecase

import tour.donnees.github.data.repository.SearchedRepository
import tour.donnees.github.domain.mapping.RepositoryMapping
import tour.donnees.github.domain.model.InfoRepositoryListModel

class GetRepositoryByPageImpl(
    private val repository: SearchedRepository
): GetRepositoryByPage {
    override suspend fun getRepositoryByPage(page: Int): InfoRepositoryListModel {
        val mapping = RepositoryMapping()
        val response = repository.getRepositoriesByPage(page)

        return InfoRepositoryListModel(
            total = response.totalCount ?: 0,
            items = response.items?.map { mapping.mapToRepository(it) } ?: emptyList()
        )
    }
}