package tour.donnees.github.domain.usecase

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import tour.donnees.github.data.dto.ItemDTO
import tour.donnees.github.data.dto.OwnerDTO
import tour.donnees.github.data.dto.SearchedRepositoriesDTO
import tour.donnees.github.data.repository.SearchedRepository

class GetRepositoryByPageTest {

    private var searchedRepository: SearchedRepository = mockk()

    private val useCase = GetRepositoryByPageImpl(searchedRepository)

    @Test
    fun `UseCase GetRepositoryByPage with Success`() {
        val value = mockSearchedRepository()

        coEvery { searchedRepository.getRepositoriesByPage(any()) } returns value

        val test = runBlocking { useCase.getRepositoryByPage(1) }

        Assert.assertEquals(value.items?.size, test.items.size)
        Assert.assertEquals(value.items?.size, test.items.size)
        Assert.assertEquals(value.totalCount, test.total)

        test.items.toList()[0].let { item ->
            Assert.assertEquals(value.items?.get(0)!!.url, item.repositoryUrl)
            Assert.assertEquals(value.items?.get(0)!!.forks, item.forks)
            Assert.assertEquals(value.items?.get(0)!!.stargazersCount, item.stars)
            Assert.assertEquals(value.items?.get(0)!!.owner!!.url, item.profileUrl)
            Assert.assertEquals(value.items?.get(0)!!.owner!!.avatarUrl, item.profileImageUrl)
            Assert.assertEquals(value.items?.get(0)!!.owner!!.login, item.profileName)
        }


    }

    private fun mockSearchedRepository(): SearchedRepositoriesDTO {
        return SearchedRepositoriesDTO(
            incompleteResults = false,
            items = listOf(
                ItemDTO(
                    name = "test",
                    owner = OwnerDTO(
                        avatarUrl = "http://test.com/avatar",
                        url = "http://test.com",
                        login = "test test"
                    ),
                    url = "http://test.com/repo",
                    stargazersCount = 10,
                    forks = 10
                )
            ),
            totalCount = 1
        )
    }
}