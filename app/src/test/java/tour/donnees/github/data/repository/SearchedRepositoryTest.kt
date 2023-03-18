package tour.donnees.github.data.repository

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import tour.donnees.github.data.api.API
import tour.donnees.github.data.dto.SearchedRepositoriesDTO

class SearchedRepositoryTest {

    private var api = mockk<API>()
    private val repository = SearchedRepositoryImpl(api)

    @Test
    fun `SearchedRepository GetRepositoryByPage with Success`() {
        coEvery { api.getRepositories(any(), any(), any()) } returns SearchedRepositoriesDTO(false, emptyList(), 1)

        val test = runBlocking { repository.getRepositoriesByPage(1) }

        Assert.assertEquals(1, test.totalCount)
        Assert.assertEquals(false, test.incompleteResults)
        Assert.assertEquals(0, test.items!!.size)
    }
}