package com.karthikdevadiga.tickit.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 2)
abstract class TickitDatabase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao

    companion object {

        private var DATABASE_INSTANCE: TickitDatabase?= null

        fun createDatabase(context: Context):TickitDatabase {

            return DATABASE_INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context,
                    TickitDatabase::class.java,
                    "Tickit Database"
                ).fallbackToDestructiveMigration()
                    .build()

                DATABASE_INSTANCE=instance
                instance
            }

        }

    }

}

