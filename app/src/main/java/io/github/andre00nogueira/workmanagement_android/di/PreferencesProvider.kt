package io.github.andre00nogueira.workmanagement_android.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesProvider {

    @Provides
    @Singleton
    fun provideAuthPreferences(@ApplicationContext context: Context) =
        context.getSharedPreferences("AUTH_PREFERENCES", Context.MODE_PRIVATE)

}