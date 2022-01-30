package io.github.andre00nogueira.workmanagement_android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.andre00nogueira.workmanagement_android.api.services.AuthService
import io.github.andre00nogueira.workmanagement_android.api.services.JobService
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ServicesProvider {

    @Singleton
    @Provides
    fun provideAuthService(retrofit: Retrofit) = retrofit.create(AuthService::class.java)

    @Singleton
    @Provides
    fun provideJobService(retrofit: Retrofit) = retrofit.create(JobService::class.java)
}