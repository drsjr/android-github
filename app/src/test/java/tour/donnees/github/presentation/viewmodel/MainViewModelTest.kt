package tour.donnees.github.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import tour.donnees.github.config.CoroutineDispatcher
import tour.donnees.github.domain.model.RepositoryModel
import tour.donnees.github.domain.usecase.GetRepositoryByPage

@ExperimentalCoroutinesApi
class MainViewModelTest {
    
    @get:Rule
    val dispatcherRule = CoroutineDispatcher()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val mockGetRepositoryByPage = mockk<GetRepositoryByPage>()

    private val viewModel = MainViewModel(mockGetRepositoryByPage)

    @Test
    fun test() {
        Assert.assertEquals(0, viewModel.getRepositoryList().size)
    }

    @Test
    fun test2() {

        coEvery { mockGetRepositoryByPage.getRepositoryByPage(any()) } returns getMockRepositories()

        runTest {
            viewModel.getRepositories()
        }

        Assert.assertEquals(1, viewModel.getRepositoryList().size)

    }

    private fun getMockRepositories(): List<RepositoryModel> {
        return listOf(
            RepositoryModel(
                repositoryName = "test repositoryName",
                repositoryUrl = "test repositoryUrl",
                repositoryDescription = "test repositoryDescription",
                stars = 123,
                forks = 321,
                profileName = "test profileName",
                profileImageUrl = "test profileImageUrl",
                profileUrl = "test profileUrl")
        )
    }

}