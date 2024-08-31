package com.github.januprasad.drinkwater.domain.use_cases

import com.github.januprasad.drinkwater.domain.model.Reminder
import com.github.januprasad.drinkwater.domain.repository.ReminderRepository
import javax.inject.Inject

class UpdateUseCase @Inject constructor(private val reminderRepository: ReminderRepository) {

    suspend operator fun invoke(reminder: Reminder) = reminderRepository.update(reminder)

}