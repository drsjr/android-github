package tour.donnees.github.data.di

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tour.donnees.github.data.api.API

val NetworkModule = module {
    single { getOkHttpClient() }
    single { getRetrofit(get()) }
    single { get<Retrofit>().create(API::class.java) }
}

private fun getOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            } else Interceptor {chain ->
                chain.proceed(chain.request())
            }
        )
        .build()
}

private fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(
            "https://api.github.com/"
        )
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
}