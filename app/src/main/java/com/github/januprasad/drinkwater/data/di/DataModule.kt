package com.github.januprasad.drinkwater.data.di

import android.content.Context
import com.github.januprasad.drinkwater.data.local.ReminderDao
import com.github.januprasad.drinkwater.data.local.ReminderDatabase
import com.github.januprasad.drinkwater.data.repository.ReminderRepoImpl
import com.github.januprasad.drinkwater.domain.repository.ReminderRepository
import com.github.januprasad.drinkwater.domain.use_cases.DeleteUseCase
import com.github.januprasad.drinkwater.domain.use_cases.GetAllReminderUseCase
import com.github.januprasad.drinkwater.domain.use_cases.InsertUseCase
import com.github.januprasad.drinkwater.domain.use_cases.ReminderUseCases
import com.github.januprasad.drinkwater.domain.use_cases.UpdateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ReminderDatabase {
        return ReminderDatabase.getInstance(context)
    }

    @Provides
    fun provideDao(reminderDatabase: ReminderDatabase): ReminderDao {
        return reminderDatabase.getReminderDao()
    }


    @Provides
    fun provideReminderRepo(reminderDao: ReminderDao): ReminderRepository {
        return ReminderRepoImpl(reminderDao)
    }

    @Provides
    fun provideReminderUseCases(reminderRepository: ReminderRepository): ReminderUseCases {
        return ReminderUseCases(
            insertUseCase = InsertUseCase(reminderRepository),
            deleteUseCase = DeleteUseCase(reminderRepository),
            updateUseCase = UpdateUseCase(reminderRepository),
            getAllReminderUseCase = GetAllReminderUseCase(reminderRepository),
        )
    }
}