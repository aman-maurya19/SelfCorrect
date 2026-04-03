package com.am.sarojselfcorrect

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [MistakeEntity::class],
    version = 3,
    exportSchema = false
)
abstract class MistakeDatabase : RoomDatabase() {

    abstract fun mistakeDao(): MistakeDao

    companion object {
        @Volatile
        private var INSTANCE: MistakeDatabase? = null

        fun getDatabase(context: Context): MistakeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MistakeDatabase::class.java,
                    "mistake_db"
                )
                    .fallbackToDestructiveMigration(false)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
