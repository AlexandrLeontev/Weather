package com.example.weatherapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey
// Чтобы описать сущность, нужно указать в классе соответствующую аннотацию @Entity.
// Также аннотацией @PrimaryKey мы указали, что в таблице будет ключ id.
// Он будет генерироваться автоматически (autoGenerate = true), и нам не надо заботится о содержании этого ключа.
const val ID = "id"
const val CITY = "city"
const val TEMPERATURE = "temperature"

@Entity
data class HistoryEntity(
// Помимо уникального ключа для каждой строки в таблице мы будем хранить название города, температуру и погодные условия
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val city: String = "",
        val temperature: Int = 0,
        val condition: String = ""
)
