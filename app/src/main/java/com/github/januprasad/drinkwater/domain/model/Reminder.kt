package com.github.januprasad.drinkwater.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Reminder(
    val name: String,
    @PrimaryKey(autoGenerate = false)
    val timeInMillis: Long,
    val done: Boolean = false,
    val isRepeat: Boolean = false
)
