package io.github.andre00nogueira.workmanagement_android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.andre00nogueira.workmanagement_android.api.services.AuthService
import io.github.andre00nogueira.workmanagement_android.repositories.AuthRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesProvider {

    @Singleton
    @Provides
    fun provideAuthRepository(authService: AuthService) = AuthRepository(authService)
}