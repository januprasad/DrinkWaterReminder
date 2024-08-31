package com.github.januprasad.drinkwater.domain.use_cases

import com.github.januprasad.drinkwater.domain.repository.ReminderRepository
import javax.inject.Inject

class GetAllReminderUseCase @Inject constructor(val repository: ReminderRepository){
    operator fun invoke() = repository.getAllReminders()
}