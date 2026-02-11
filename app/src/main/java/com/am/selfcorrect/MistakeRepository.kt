package com.am.selfcorrect

import androidx.lifecycle.LiveData

class MistakeRepository(private val dao: MistakeDao) {

    val allMistakes = dao.getAllMistakes()

    suspend fun addMistake(mistake: MistakeEntity) {
        dao.insertMistake(mistake)
    }

    suspend fun getRepeatCount(title: String): Int {
        return dao.countRepeat(title)
    }


    fun getLessons() = dao.getLessons()

    fun getTotalMistakeCount(): LiveData<Int> =
        dao.getTotalMistakeCount()


}

