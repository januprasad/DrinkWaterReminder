package com.github.januprasad.drinkwater.domain.use_cases

data class ReminderUseCases(
    val getAllReminderUseCase: GetAllReminderUseCase,
    val deleteUseCase: DeleteUseCase,
    val insertUseCase: InsertUseCase,
    val updateUseCase: UpdateUseCase,
)
