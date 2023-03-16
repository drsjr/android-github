package tour.donnees.github.domain.model

data class RepositoryModel(
    val repositoryName: String,
    val repositoryUrl: String,
    val repositoryDescription: String,
    val stars: Int,
    val forks: Int,
    val profileName: String,
    val profileImageUrl: String,
    val profileUrl: String

)