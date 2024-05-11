package com.jsolutions.imcads

import android.app.Application
import androidx.room.Room
import com.jsolutions.imcads.data.AppDatabase

class App : Application() {
    companion object {
        lateinit var database: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()

        database =
            Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "app-database",
            ).build()
    }
}
