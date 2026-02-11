package com.am.selfcorrect

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MistakeRepository
    val mistakes: LiveData<List<MistakeEntity>>

    init {
        val dao = MistakeDatabase
            .getDatabase(application)
            .mistakeDao()

        repository = MistakeRepository(dao)
        mistakes = repository.allMistakes
    }
}

