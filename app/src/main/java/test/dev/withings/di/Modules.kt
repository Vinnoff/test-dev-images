package test.dev.withings.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import test.dev.withings.BuildConfig
import test.dev.withings.data.remote.PixabayService
import test.dev.withings.data.remote.source.RemoteDataSource
import test.dev.withings.data.repo.ImagesRepository
import test.dev.withings.data.repo.ImagesRepositoryImpl
import timber.log.Timber
import java.util.concurrent.TimeUnit

class Modules {
    companion object {
        val rootModule = module {
            single<CoroutineDispatcher> { Dispatchers.Main }
            single { createOkHttpClient() }
        }

        val dataModule = module {
            single { RemoteDataSource(get()) }

            single<PixabayService> { createPixabayWebService(get()) }

            single<ImagesRepository> { ImagesRepositoryImpl(get()) }
        }

        val domainModule = module {
        }

        val presentationModule = module {
        }
    }
}

fun createOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(10, TimeUnit.SECONDS)
    .readTimeout(10, TimeUnit.SECONDS)
    .addNetworkInterceptor(StethoInterceptor())
    .addNetworkInterceptor(HttpLoggingInterceptor { message -> Timber.tag("okhttp").d(message) }.apply { level = HttpLoggingInterceptor.Level.BODY })
    .build()

inline fun <reified T> createPixabayWebService(okHttpClient: OkHttpClient): T {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.HOST)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create()
}