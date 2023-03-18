package tour.donnees.github.domain.usecase

import tour.donnees.github.domain.model.InfoRepositoryListModel

interface GetRepositoryByPage {
    suspend fun getRepositoryByPage(page: Int): InfoRepositoryListModel
}