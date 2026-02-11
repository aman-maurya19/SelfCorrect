package com.am.selfcorrect

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AddMistakeViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: MistakeRepository

    init {
        val dao = MistakeDatabase.getDatabase(application).mistakeDao()
        repo = MistakeRepository(dao)
    }

    fun addMistake(mistake: MistakeEntity) = viewModelScope.launch {
        repo.addMistake(mistake)
    }
}
