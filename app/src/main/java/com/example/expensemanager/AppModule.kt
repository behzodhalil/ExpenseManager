package com.example.expensemanager

import android.app.Application
import com.example.expensemanager.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@InstallIn(ActivityComponent::class)
@Module
class AppModule {
    @Singleton
    @Provides
    fun provideNoteDatabase(application: Application): AppDatabase {
        return AppDatabase.invoke(application.applicationContext)
    }
}