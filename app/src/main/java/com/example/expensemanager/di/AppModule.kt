package com.example.expensemanager.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.expensemanager.data.local.AppDatabase
import com.example.expensemanager.data.local.ExpenseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(application: Application): AppDatabase {
        return AppDatabase.invoke(application.applicationContext)
    }
}