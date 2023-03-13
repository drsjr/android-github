package tour.donnees.github.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import tour.donnees.github.data.dto.SearchedRepositoriesDTO

interface API {
    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String = "language:kotlin",
        @Query("sort") sort: String = "stars",
        @Query("page") page: Int = 1
    ): SearchedRepositoriesDTO
}