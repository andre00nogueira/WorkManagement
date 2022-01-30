package io.github.andre00nogueira.workmanagement_android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.andre00nogueira.workmanagement_android.api.services.AuthService
import io.github.andre00nogueira.workmanagement_android.api.services.JobService
import io.github.andre00nogueira.workmanagement_android.preferences.AuthPreferences
import io.github.andre00nogueira.workmanagement_android.repositories.AuthRepository
import io.github.andre00nogueira.workmanagement_android.repositories.JobRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesProvider {

    @Singleton
    @Provides
    fun provideAuthRepository(preferences: AuthPreferences, authService: AuthService) =
        AuthRepository(preferences, authService)

    @Singleton
    @Provides
    fun provideJobRepository(preferences: AuthPreferences, jobService: JobService) =
        JobRepository(preferences, jobService)
}