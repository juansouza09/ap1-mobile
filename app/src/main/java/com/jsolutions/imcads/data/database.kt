package com.jsolutions.imcads.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jsolutions.imcads.data.models.User
import com.jsolutions.imcads.data.models.UserDao

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
