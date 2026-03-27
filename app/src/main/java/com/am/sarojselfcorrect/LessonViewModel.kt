package com.am.sarojselfcorrect

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class LessonViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: MistakeRepository

    val lessons: LiveData<List<MistakeEntity>>
    val totalMistakes: LiveData<Int>

    init {
        val dao = MistakeDatabase.getDatabase(application).mistakeDao()
        repo = MistakeRepository(dao)

        lessons = repo.getLessons()
        totalMistakes = repo.getTotalMistakeCount()
    }
}
