package com.rich_kirk.infinityquiz.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.rich_kirk.infinityquiz.data.QuestionDatabase
import com.rich_kirk.infinityquiz.model.Question
import com.rich_kirk.infinityquiz.repository.QuestionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuestionViewModel(application: Application): AndroidViewModel(application) {
    private var question: Question
    private val repository:QuestionRepository


    init {
        val questionDao = QuestionDatabase.getDatabase(
            application
        ).questionDao()
        repository = QuestionRepository(questionDao)
        question = Question(0, "", "", "", "", "", "", "", 0, 0, "")
    }


    fun readRandomQuestion(): Question {
        viewModelScope.launch(Dispatchers.IO) {
            question = repository.readRandomQuestion()
        }
        return question
    }
}
