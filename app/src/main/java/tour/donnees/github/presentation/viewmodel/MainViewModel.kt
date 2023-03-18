package tour.donnees.github.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tour.donnees.github.domain.model.RepositoryModel
import tour.donnees.github.domain.usecase.GetRepositoryByPage

class MainViewModel(
    private val getRepositoryByPage: GetRepositoryByPage
): ViewModel() {

    private var hasMore = false
    private var page: Int = 1
    private val repositoriesList = mutableListOf<RepositoryModel>()

    private val _list = MutableLiveData<List<RepositoryModel>>()
    val list = _list.asLiveData()

    private val _isLoading = MutableLiveData(false)
    val isLoading = _isLoading.asLiveData()

    private val _navigateToViewState = MutableLiveData<ViewState>()
    val navigateToViewState = _navigateToViewState.asLiveData()

    fun getRepositories() {
        _isLoading.startLoading()
        viewModelScope.launch {
            val info = getRepositoryByPage.getRepositoryByPage(page)
            repositoriesList.addAll(info.items)
            hasMore = repositoriesList.size < info.total
            _list.postValue(repositoriesList)
            _isLoading.stopLoading()
            page++
        }
    }

    fun getRepositoryList(): List<RepositoryModel> = repositoriesList.toList()
    fun isLoading() = isLoading.value ?: false
    fun hasMorePages(): Boolean = hasMore

}

sealed class ViewState {
    object ViewLoading: ViewState()
    object ViewEmpty: ViewState()
    object ViewList: ViewState()
    object ViewInfo: ViewState()
}

fun MutableLiveData<Boolean>.startLoading() {
    this.postValue(true)
}

fun MutableLiveData<Boolean>.stopLoading() {
    this.postValue(false)
}

fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> {
    return this
}