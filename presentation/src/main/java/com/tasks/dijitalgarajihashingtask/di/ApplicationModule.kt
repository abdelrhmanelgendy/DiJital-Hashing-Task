package com.tasks.dijitalgarajihashingtask.di

import com.tasks.data.webservice.DijitalApi
import com.tasks.domain.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object
ApplicationModule {


    @Provides
    @BaseUrl
    @Singleton
    fun provideBaseUrl() = Constant.DIJITALBASE_URL



    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }



    @Provides
    @Singleton
    fun provideGitHupApi(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): DijitalApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(DijitalApi::class.java)





}