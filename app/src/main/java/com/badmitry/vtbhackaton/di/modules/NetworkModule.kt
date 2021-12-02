package com.badmitry.vtbhackaton.di.modules

import android.app.Application
import com.badmitry.data.AndroidNetworkChecker
import com.badmitry.data.api.VTBAuthApi
import com.badmitry.data.api.YandexPartitionsApi
import com.badmitry.data.repositories.VTBAuthRepositories
import com.badmitry.data.repositories.YandexPartitionsRepositories
import com.badmitry.domain.repositories.INetworkChecker
import com.badmitry.domain.repositories.IVTBAuthRepositories
import com.badmitry.domain.repositories.IYandexPartitionsRepositories
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun networkChecker(app: Application): INetworkChecker = AndroidNetworkChecker(app)

    @Singleton
    @Provides
    fun gson() = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Provides
    @Singleton
    fun client(): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        .build()

    @Provides
    @Singleton
    @Named("vtbAuth")
    fun vtbAuthRetrofit(
        client: OkHttpClient,
        gson: Gson
    ) = Retrofit.Builder()
        .baseUrl("https://hackaton.bankingapi.ru")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build()
        .create(VTBAuthApi::class.java)

    @Provides
    @Singleton
    @Named("yandex")
    fun yandexRetrofit(
        client: OkHttpClient,
        gson: Gson
    ) = Retrofit.Builder()
        .baseUrl("https://search-maps.yandex.ru")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build()
        .create(YandexPartitionsApi::class.java)

    @Provides
    @Singleton
    @Named("vtbAuth")
    fun authRepository(@Named("vtbAuth") api: VTBAuthApi): IVTBAuthRepositories =
        VTBAuthRepositories(api)

    @Provides
    @Singleton
    @Named("yandex")
    fun yandexRepository(@Named("yandex") api: YandexPartitionsApi): IYandexPartitionsRepositories =
        YandexPartitionsRepositories(api)


//    @Singleton
//    @Provides
//    fun networkStatus(app: App): INetworkChecker = AndroidNetworkChecker(app)
//
//    @Singleton
//    @Provides
//    fun client() = OkHttpClient.Builder()
//        .readTimeout(30, TimeUnit.SECONDS)
//        .connectTimeout(30, TimeUnit.SECONDS)
//        .addNetworkInterceptor(HttpLoggingInterceptor().apply {
//            level = HttpLoggingInterceptor.Level.BODY
//        })
//        .build()
//
//    @Singleton
//    @Provides
//    fun gson() = GsonBuilder()
//        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//        .excludeFieldsWithoutExposeAnnotation()
//        .create()
//
//    @Singleton
//    @Provides
//    @Named("oAuth")
//    fun getBuilder(gson: Gson, client: OkHttpClient) = Retrofit.Builder()
//        .client(client)
//        .baseUrl(API_BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create(gson))
//        .build()


//
//    @Singleton
//    @Provides
//    fun api(gson: Gson, client: OkHttpClient) = Retrofit.Builder()
//        .baseUrl("https://api.github.com")
//        .client(client)
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .addConverterFactory(GsonConverterFactory.create(gson))
//        .build()
//        .create(IDataSource::class.java)
}
