package com.badmitry.vtbhackaton.di.modules

import dagger.Module

@Module
class NetworkModule {

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
