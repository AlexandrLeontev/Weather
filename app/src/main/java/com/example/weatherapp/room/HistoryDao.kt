package com.example.weatherapp.room

import android.database.Cursor
import androidx.room.*
//Теперь создадим объект доступа к данным. Для этого надо создать интерфейс с аннотацией @Dao
@Dao
interface HistoryDao {
//Аннотации @Insert, @Update, @Delete управляют модификацией данных. Аннотация @Query создаёт запросы к базе данных.
// Параметры в запросе задаются через двоеточие.
// Если поле, которое мы хотим добавить, уже существует,
// можно выбрать соответствующую стратегию OnConflictStrategy (IGNORE, ABORT, REPLACE)

    @Query("SELECT * FROM HistoryEntity")
    fun all(): List<HistoryEntity>

    @Query("SELECT * FROM HistoryEntity WHERE city LIKE :city")
    fun getDataByWord(city: String): List<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: HistoryEntity)

    @Update
    fun update(entity: HistoryEntity)

    @Delete
    fun delete(entity: HistoryEntity)

    @Query("DELETE FROM HistoryEntity WHERE id = :id")
    fun deleteById(id: Long)

    @Query("SELECT id, city, temperature FROM HistoryEntity")
    fun getHistoryCursor(): Cursor

    @Query("SELECT id, city, temperature FROM HistoryEntity WHERE id = :id")
    fun getHistoryCursor(id: Long): Cursor
}
