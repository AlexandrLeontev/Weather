package com.example.weatherapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
// Теперь разберём объект базы данных.
// Это последний класс в нашем package room. Для этого надо создать класс, наследующий RoomDatabase

//Этот класс должен быть абстрактным, и у него должна быть определена аннотация @Database со всеми табличными объектами.
// В нашем случае это массив всего с одной таблицей. Версию БД указываем в version.
//Не забывайте увеличивать версию, если меняете структуру своей БД
@Database(entities = arrayOf(HistoryEntity::class), version = 1, exportSchema = false)
//Параметр exportSchema указывает, создавать или нет файлик в проекте, где будет храниться история версий вашей БД
//Это совершенно необязательно, но в коммерческих проектах это хорошая практика

abstract class HistoryDataBase : RoomDatabase() {
//Также в классе необходимо определить метод, возвращающий объект доступа к данным. У нас это метод historyDao
    abstract fun historyDao(): HistoryDao
}
