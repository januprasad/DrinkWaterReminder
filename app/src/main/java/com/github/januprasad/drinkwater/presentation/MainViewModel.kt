package com.github.januprasad.drinkwater.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.januprasad.drinkwater.domain.model.Reminder
import com.github.januprasad.drinkwater.domain.use_cases.GetAllReminderUseCase
import com.github.januprasad.drinkwater.domain.use_cases.ReminderUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: ReminderUseCases
) : ViewModel() {

    val uiState = useCase.getAllReminderUseCase.invoke().map { UiState(it) }
        .stateIn(viewModelScope, started = SharingStarted.Eagerly, UiState())

    fun insert(reminder: Reminder) = viewModelScope.launch {
        useCase.insertUseCase.invoke(reminder)
    }

    fun update(reminder: Reminder) = viewModelScope.launch {
        useCase.updateUseCase.invoke(reminder)
    }

    fun delete(reminder: Reminder) = viewModelScope.launch {
        useCase.deleteUseCase.invoke(reminder)
    }
}

data class UiState(
    val data: List<Reminder> = emptyList()
)
