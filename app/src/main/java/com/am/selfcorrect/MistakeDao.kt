package com.am.selfcorrect

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MistakeDao {

    @Insert
    suspend fun insertMistake(mistake: MistakeEntity)

    @Query("SELECT * FROM mistakes ORDER BY timestamp  DESC")
    fun getAllMistakes(): LiveData<List<MistakeEntity>>

    @Query("SELECT * FROM mistakes WHERE lesson IS NOT NULL AND lesson != ''")
    fun getLessons(): LiveData<List<MistakeEntity>>

    @Query("SELECT COUNT(*) FROM mistakes WHERE title = :title")
    suspend fun countRepeat(title: String): Int

    @Query("SELECT * FROM mistakes WHERE category = :cat")
    fun filterByCategory(cat: String): LiveData<List<MistakeEntity>>

    @Query("SELECT COUNT(*) FROM mistakes")
    fun getTotalMistakeCount(): LiveData<Int>


}
