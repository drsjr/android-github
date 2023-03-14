package tour.donnees.github.domain.usecase

import tour.donnees.github.domain.model.RepositoryModel

interface GetRepositoryByPage {
    suspend fun getRepositoryByPage(page: Int): Collection<RepositoryModel>
}