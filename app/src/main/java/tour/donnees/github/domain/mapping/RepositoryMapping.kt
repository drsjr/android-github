package tour.donnees.github.domain.mapping

import tour.donnees.github.data.dto.ItemDTO
import tour.donnees.github.domain.model.RepositoryModel

class RepositoryMapping {

    fun mapToRepository(item: ItemDTO): RepositoryModel {
        return RepositoryModel(
            repositoryName = item.name.orEmpty(),
            repositoryUrl = item.url.orEmpty(),
            repositoryDescription = item.description.orEmpty(),
            stars = item.stargazersCount ?: 0,
            forks = item.forks ?: 0,
            profileName = item.owner?.login.orEmpty(),
            profileImageUrl = item.owner?.avatarUrl.orEmpty(),
            profileUrl = item.owner?.url.orEmpty()
        )

    }
}