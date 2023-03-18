package tour.donnees.github.config

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tour.donnees.github.data.api.API
import tour.donnees.github.data.repository.SearchedRepository
import tour.donnees.github.data.repository.SearchedRepositoryImpl
import tour.donnees.github.domain.model.RepositoryModel
import tour.donnees.github.domain.usecase.GetRepositoryByPage
import tour.donnees.github.domain.usecase.GetRepositoryByPageImpl
import tour.donnees.github.presentation.viewmodel.MainViewModel


val MockNetwork = module {
    single<API> { getAPI() }
}

val MockRepository = module {
    factory<SearchedRepository> { SearchedRepositoryImpl(get())  }
}

val MockUseCase  = module {
    factory<GetRepositoryByPage> { GetRepositoryByPageImpl(get()) }
}

val MockViewModel = module {
    viewModel { MainViewModel(get()) }
}

fun getAPI(): API {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(MockInterceptor())
        .build()
    val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(
            "https://api.github.com/"
        )
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    val api = retrofit.create(API::class.java)

    //coEvery { api.getRepositories(any(), any(), any()) } returns SearchedRepositoriesDTO()

    return api

}