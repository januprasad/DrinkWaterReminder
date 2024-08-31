package com.github.januprasad.drinkwater.data.repository

import com.github.januprasad.drinkwater.data.local.ReminderDao
import com.github.januprasad.drinkwater.domain.model.Reminder
import com.github.januprasad.drinkwater.domain.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow

class ReminderRepoImpl(private val reminderDao: ReminderDao) : ReminderRepository {
    override suspend fun insert(reminder: Reminder) {
        reminderDao.insert(reminder)
    }

    override suspend fun update(reminder: Reminder) {
        reminderDao.update(reminder)
    }

    override suspend fun delete(reminder: Reminder) {
        reminderDao.delete(reminder)

    }

    override fun getAllReminders(): Flow<List<Reminder>> = reminderDao.getAllReminder()
}