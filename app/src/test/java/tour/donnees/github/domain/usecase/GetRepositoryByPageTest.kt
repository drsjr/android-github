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
    fun test() {
        val value = mockSearchedRepository()

        coEvery { searchedRepository.getRepositoriesByPage(any()) } returns value

        val test = runBlocking { useCase.getRepositoryByPage(1) }.toList()

        Assert.assertEquals(value.items?.size, test.size)
        Assert.assertEquals(value.items?.get(0)!!.url, test[0].repositoryUrl)
        Assert.assertEquals(value.items?.get(0)!!.forks, test[0].forks)
        Assert.assertEquals(value.items?.get(0)!!.stargazersCount, test[0].stars)
        Assert.assertEquals(value.items?.get(0)!!.owner!!.url, test[0].profileUrl)
        Assert.assertEquals(value.items?.get(0)!!.owner!!.avatarUrl, test[0].profileImageUrl)
        Assert.assertEquals(value.items?.get(0)!!.fullName, test[0].profileName)
        Assert.assertEquals(value.items?.size, test.size)

    }

    private fun mockSearchedRepository(): SearchedRepositoriesDTO {
        return SearchedRepositoriesDTO(
            incompleteResults = false,
            items = listOf(
                ItemDTO(
                    name = "test",
                    owner = OwnerDTO(
                        avatarUrl = "http://test.com/avatar",
                        url = "http://test.com"
                    ),
                    url = "http://test.com/repo",
                    stargazersCount = 10,
                    forks = 10,
                    fullName = "test test"
                )
            ),
            totalCount = 1
        )
    }
}